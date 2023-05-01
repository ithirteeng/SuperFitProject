package com.ithirteeng.superfitproject.signup.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.signup.presentation.model.CompletionModel
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpModel
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpTextFieldType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpScreenViewModel : ViewModel() {

    fun accept(signUpEvent: SignUpEvent) {
        when (signUpEvent) {
            is SignUpEvent.DismissError -> _state.value = _state.value.copy(error = null)
            is SignUpEvent.Initial -> initState()
            is SignUpEvent.SignInButtonClick -> _state.value = _state.value.copy(
                completionModel = CompletionModel(isSignInButtonPressed = true)
            )

            is SignUpEvent.SignUpButtonClick -> TODO()
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


}