package com.ithirteeng.superfitproject.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetAccessTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetRefreshTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.SaveTokenLocallyUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateCodeUseCase
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateEmailUseCase
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateRepeatCodeUseCase
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateUserNameUseCase
import com.ithirteeng.superfitproject.signup.domain.usecase.RegisterUseCase
import com.ithirteeng.superfitproject.signup.presentation.model.CompletionModel
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpModel
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpTextFieldType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpScreenViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val validateCodeUseCase: ValidateCodeUseCase,
    private val validateRepeatCodeUseCase: ValidateRepeatCodeUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val saveTokenLocallyUseCase: SaveTokenLocallyUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    fun accept(signUpEvent: SignUpEvent) {
        when (signUpEvent) {
            is SignUpEvent.DismissError -> _state.value = _state.value.copy(error = null)
            is SignUpEvent.Initial -> initState()
            is SignUpEvent.SignInButtonClick -> _state.value = _state.value.copy(
                completionModel = CompletionModel(isSignInButtonPressed = true)
            )

            is SignUpEvent.SignUpButtonClick -> onSignUpButtonClick()
            is SignUpEvent.TextFieldChange -> onTextFieldChange(signUpEvent.type, signUpEvent.value)
        }
    }

    private val _state = MutableStateFlow(SignUpState())

    val state: StateFlow<SignUpState> = _state

    private fun initState() {
        _state.value = _state.value.copy(
            isLoading = false,
            error = null,
            data = SignUpModel(),
            completionModel = CompletionModel()
        )
    }

    private fun onTextFieldChange(textFieldType: SignUpTextFieldType, value: String) {
        when (textFieldType) {
            SignUpTextFieldType.EMAIL -> _state.value = _state.value.copy(
                data = _state.value.data.copy(email = value)
            )

            SignUpTextFieldType.PASSWORD -> _state.value = _state.value.copy(
                data = _state.value.data.copy(code = value)
            )

            SignUpTextFieldType.REPEAT_PASSWORD -> _state.value = _state.value.copy(
                data = _state.value.data.copy(repeatCode = value)
            )

            SignUpTextFieldType.USER_NAME -> _state.value = _state.value.copy(
                data = _state.value.data.copy(userName = value)
            )
        }
    }

    private fun onSignUpButtonClick() {
        if (validateFields()) {
            registerUser()
        }
    }

    private fun validateFields(): Boolean {
        var flag = false
        if (!validateEmailUseCase(_state.value.data.email)) {
            _state.value = _state.value.copy(
                error = ErrorEntity(Exception(), R.string.incorrect_email)
            )
        } else if (!validateUserNameUseCase(_state.value.data.userName)) {
            _state.value = _state.value.copy(
                error = ErrorEntity(Exception(), R.string.incorrect_username)
            )
        } else if (!validateCodeUseCase(_state.value.data.code)) {
            _state.value = _state.value.copy(
                error = ErrorEntity(Exception(), R.string.code_error)
            )
        } else if (
            !validateRepeatCodeUseCase(_state.value.data.code, _state.value.data.repeatCode)
        ) {
            _state.value = _state.value.copy(
                error = ErrorEntity(Exception(), R.string.repeat_code_error)
            )
        } else {
            flag = true
        }
        return flag
    }

    private fun registerUser() {
        val entity = LoginEntity(
            login = _state.value.data.email,
            password = _state.value.data.code
        )
        viewModelScope.launch {
            registerUseCase(loginEntity = entity)
                .onSuccess {
                    getRefreshToken()
                }.onFailure {
                    _state.value = _state.value.copy(
                        error = ErrorHelper.setupErrorEntity(it, R.string.user_name_exists),
                        isLoading = false
                    )
                }
        }
    }

    private fun getRefreshToken() {
        viewModelScope.launch {
            getRefreshTokenUseCase(
                LoginEntity(
                    login = _state.value.data.email,
                    password = _state.value.data.code
                )
            ).onSuccess {
                getAccessToken(refreshToken = it.refreshToken)
            }.onFailure {
                _state.value = _state.value.copy(
                    error = ErrorHelper.setupErrorEntity(it, R.string.user_name_exists),
                    isLoading = false
                )
            }
        }
    }


    private fun getAccessToken(refreshToken: String) {
        viewModelScope.launch {
            getAccessTokenUseCase(refreshToken = refreshToken)
                .onSuccess {
                    saveTokenLocallyUseCase(
                        TokenEntity(
                            userName = _state.value.data.email,
                            password = _state.value.data.code,
                            refreshToken = refreshToken,
                            accessToken = it.accessToken
                        )
                    )
                    _state.value = _state.value.copy(
                        isLoading = false,
                        completionModel = CompletionModel()
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        error = ErrorHelper.setupErrorEntity(it, R.string.user_name_exists),
                        isLoading = false
                    )
                }
        }
    }

}