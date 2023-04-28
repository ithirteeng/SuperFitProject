package com.ithirteeng.superfitproject.signin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInScreenViewModel : ViewModel() {

    fun accept(signInEvent: SignInEvent) {
        when (signInEvent) {
            is SignInEvent.ChangeTextField -> changeTextFieldValue(signInEvent.value)
            is SignInEvent.SignInButtonCLick -> {}
            is SignInEvent.SignUpButtonClick -> {}
            else -> initData()
        }
    }

    private val _state = MutableLiveData(SignInState())

    val state: LiveData<SignInState> = _state

    private fun initData() {
        _state.value = _state.value?.copy(
            isLoading = false
        )
    }

    private fun changeTextFieldValue(value: String) {
        _state.value = _state.value?.copy(
            textFieldValue = value
        )
    }
}