package com.ithirteeng.superfitproject.signin.presentation

sealed class SignInEvent {
    object Initialize : SignInEvent()
    class ChangeTextField(val value: String) : SignInEvent()
    object SignInButtonCLick : SignInEvent()
    object SignUpButtonClick : SignInEvent()
}