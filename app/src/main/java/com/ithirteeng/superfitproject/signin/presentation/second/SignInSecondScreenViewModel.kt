package com.ithirteeng.superfitproject.signin.presentation.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetAccessTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetRefreshTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.RemoveCurrentUserNameUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.SaveTokenLocallyUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.launch

class SignInSecondScreenViewModel(
    private val removeCurrentUserNameUseCase: RemoveCurrentUserNameUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val saveTokenLocallyUseCase: SaveTokenLocallyUseCase,
) : ViewModel() {

    fun accept(signInSecondIntent: SignInSecondIntent) {
        when (signInSecondIntent) {
            is SignInSecondIntent.NumberButtonClick -> onNumberClick(
                list = signInSecondIntent.list,
                number = signInSecondIntent.number
            )

            is SignInSecondIntent.BackButtonClick -> onBackButtonClick()
            SignInSecondIntent.DismissError -> dismissError()
            is SignInSecondIntent.Initial -> initState(signInSecondIntent.userName)
        }
    }

    private val _state = MutableLiveData(SignInSecondState())

    val state: LiveData<SignInSecondState> = _state

    private fun initState(userName: String) {
        _state.value = _state.value?.copy(
            userName = userName,
            completionModel = CompletionModel(),
            isLoading = false,
            password = "",
            error = null
        )
    }

    private fun onNumberClick(list: List<Int>, number: String) {
        var password = getPassword(number)
        if (password.length == 4) {
            doOnPasswordFilled(password = password)
            password = ""
        }
        _state.value = _state.value?.copy(
            numbers = list.shuffled(),
            password = password
        )
    }

    private fun getPassword(number: String): String {
        val password = _state.value?.password
        return if (password != null) {
            password + number
        } else {
            ""
        }
    }

    private fun doOnPasswordFilled(password: String) {
        _state.value = _state.value?.copy(isLoading = true)
        getRefreshToken(password)
    }

    private fun getRefreshToken(password: String) {
        viewModelScope.launch {
            getRefreshTokenUseCase(
                LoginEntity(
                    login = _state.value?.userName.toString(),
                    password = password
                )
            ).onSuccess {
                getAccessToken(refreshToken = it.refreshToken, password)
            }.onFailure {
                _state.value = _state.value?.copy(
                    error = ErrorHelper.setupErrorEntity(it, R.string.incorrect_password_or_email),
                    isLoading = false
                )
            }
        }
    }

    private fun dismissError() {
        _state.value = _state.value?.copy(
            error = null,
            isLoading = false
        )
    }

    private fun getAccessToken(refreshToken: String, password: String) {
        viewModelScope.launch {
            getAccessTokenUseCase(refreshToken = refreshToken)
                .onSuccess {
                    saveTokenLocallyUseCase(
                        TokenEntity(
                            userName = _state.value?.userName.toString(),
                            password = password,
                            refreshToken = refreshToken,
                            accessToken = it.accessToken
                        )
                    )
                    _state.value = _state.value?.copy(
                        isLoading = false,
                        completionModel = CompletionModel(
                            isCompleted = true,
                            isBackButtonPressed = false
                        )
                    )
                }
                .onFailure {
                    _state.value = _state.value?.copy(
                        error = ErrorHelper.setupErrorEntity(
                            it,
                            R.string.incorrect_password_or_email
                        ),
                        isLoading = false
                    )
                }
        }
    }

    private fun onBackButtonClick() {
        removeCurrentUserNameUseCase()
        _state.value = _state.value?.copy(
            password = "",
            completionModel = CompletionModel(
                isCompleted = true,
                isBackButtonPressed = true
            )
        )
    }
}