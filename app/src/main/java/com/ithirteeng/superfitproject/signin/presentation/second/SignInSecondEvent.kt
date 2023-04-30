package com.ithirteeng.superfitproject.signin.presentation.second

sealed class SignInSecondEvent {
    class Initial(val userName: String) : SignInSecondEvent()
    class NumberButtonClick(
        val number: String,
        val list: List<Int>,
    ) : SignInSecondEvent()

    object BackButtonClick : SignInSecondEvent()
    object DismissError : SignInSecondEvent()
}
