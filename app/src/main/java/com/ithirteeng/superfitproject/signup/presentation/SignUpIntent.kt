package com.ithirteeng.superfitproject.signup.presentation

import com.ithirteeng.superfitproject.signup.presentation.model.SignUpTextFieldType

sealed class SignUpIntent {
    object SignUpButtonClick : SignUpIntent()
    object SignInButtonClick : SignUpIntent()
    object Initial : SignUpIntent()
    object DismissError : SignUpIntent()
    class TextFieldChange(val type: SignUpTextFieldType, val value: String): SignUpIntent()
}
