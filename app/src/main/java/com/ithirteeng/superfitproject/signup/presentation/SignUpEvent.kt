package com.ithirteeng.superfitproject.signup.presentation

sealed class SignUpEvent {
    class SignUpButtonClick(signUpModel: SignUpModel) : SignUpEvent()
    object SignInButtonClick : SignUpEvent()
    object Initial : SignUpEvent()
}
