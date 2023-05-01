package com.ithirteeng.superfitproject.signup.presentation

import com.ithirteeng.superfitproject.signup.presentation.model.SignUpTextFieldType

sealed class SignUpEvent {
    object SignUpButtonClick : SignUpEvent()
    object SignInButtonClick : SignUpEvent()
    object Initial : SignUpEvent()
    object DismissError : SignUpEvent()
    class TextFieldChange(val type: SignUpTextFieldType, val value: String): SignUpEvent()
}
