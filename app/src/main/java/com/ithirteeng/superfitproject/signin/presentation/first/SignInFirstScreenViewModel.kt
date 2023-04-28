package com.ithirteeng.superfitproject.signin.presentation.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInFirstScreenViewModel : ViewModel() {

    fun accept(signInFirstEvent: SignInFirstEvent) {
        when (signInFirstEvent) {
            is SignInFirstEvent.ChangeTextField -> changeTextFieldValue(signInFirstEvent.value)
            is SignInFirstEvent.SignInFirstButtonCLick -> {}
            is SignInFirstEvent.SignUpButtonClickFirst -> {}
            else -> {}
        }
    }

    private val _state = MutableLiveData(SignInFirstState(isLoading = false))

    val state: LiveData<SignInFirstState> = _state

    private fun changeTextFieldValue(value: String) {
        _state.value = _state.value?.copy(
            textFieldValue = value
        )
    }
}