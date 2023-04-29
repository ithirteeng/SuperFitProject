package com.ithirteeng.superfitproject.signin.presentation.first

sealed class SignInFirstEvent {
    class ChangeTextField(val value: String) : SignInFirstEvent()
    class SignInFirstButtonCLick(val userName: String) : SignInFirstEvent()
    object SignUpButtonClickFirst : SignInFirstEvent()
}