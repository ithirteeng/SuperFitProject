package com.ithirteeng.superfitproject.signin.presentation.first

sealed class SignInFirstEvent {
    object Initial : SignInFirstEvent()
    class ChangeTextField(val value: String) : SignInFirstEvent()
    class SignInFirstButtonCLick(val userName: String) : SignInFirstEvent()
    object SignUpButtonClickFirst : SignInFirstEvent()
    object DismissError : SignInFirstEvent()
}