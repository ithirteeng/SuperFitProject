package com.ithirteeng.superfitproject.signin.presentation.second

sealed class SignInSecondEvent {
    object Initialize : SignInSecondEvent()
    class NumberButtonClick(
        val number: String,
        val list: List<Int>
    ) : SignInSecondEvent()
    object BackButtonClick : SignInSecondEvent()
}
