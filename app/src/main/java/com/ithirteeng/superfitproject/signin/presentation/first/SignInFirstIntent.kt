package com.ithirteeng.superfitproject.signin.presentation.first

sealed class SignInFirstIntent {
    object Initial : SignInFirstIntent()
    class ChangeTextField(val value: String) : SignInFirstIntent()
    class SignInFirstButtonCLick(val userName: String) : SignInFirstIntent()
    object SignUpButtonClickFirst : SignInFirstIntent()
    object DismissError : SignInFirstIntent()
}