package com.ithirteeng.superfitproject.signup.presentation

import com.ithirteeng.superfitproject.signup.presentation.model.SignUpModel
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpTextField

sealed class SignUpEvent {
    object SignUpButtonClick : SignUpEvent()
    object SignInButtonClick : SignUpEvent()
    object Initial : SignUpEvent()
    object DismissError : SignUpEvent()
    class TextFieldChange(val type: SignUpTextField, val value: String): SignUpEvent()
}
