package com.ithirteeng.superfitproject.signin.presentation.second

sealed class SignInSecondEvent {
    object Initialize : SignInSecondEvent()
    class NumberButtonClick(number: String) : SignInSecondEvent()
    object BackButtonClick : SignInSecondEvent()
}
