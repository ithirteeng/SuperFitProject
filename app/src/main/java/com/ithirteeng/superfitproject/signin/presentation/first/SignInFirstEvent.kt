package com.ithirteeng.superfitproject.signin.presentation.first

sealed class SignInFirstEvent {
    object Initialize : SignInFirstEvent()
    class ChangeTextField(val value: String) : SignInFirstEvent()
    object SignInFirstButtonCLick : SignInFirstEvent()
    object SignUpButtonClickFirst : SignInFirstEvent()
}