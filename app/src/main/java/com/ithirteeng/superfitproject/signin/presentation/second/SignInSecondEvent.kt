package com.ithirteeng.superfitproject.signin.presentation.second

sealed class SignInSecondEvent {
    class NumberButtonClick(
        val number: String,
        val list: List<Int>,
    ) : SignInSecondEvent()

    object BackButtonClick : SignInSecondEvent()
    object DismissError : SignInSecondEvent()
}
