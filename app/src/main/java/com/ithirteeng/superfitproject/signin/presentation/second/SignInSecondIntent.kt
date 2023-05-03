package com.ithirteeng.superfitproject.signin.presentation.second

sealed class SignInSecondIntent {
    class Initial(val userName: String) : SignInSecondIntent()
    class NumberButtonClick(
        val number: String,
        val list: List<Int>,
    ) : SignInSecondIntent()

    object BackButtonClick : SignInSecondIntent()
    object DismissError : SignInSecondIntent()
}
