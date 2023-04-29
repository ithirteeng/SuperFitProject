package com.ithirteeng.superfitproject.signin.presentation.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInSecondScreenViewModel : ViewModel() {

    fun accept(signInSecondEvent: SignInSecondEvent) {
        when (signInSecondEvent) {
            is SignInSecondEvent.NumberButtonClick -> {
                onNumberClick(list = signInSecondEvent.list)
            }

            else -> {}
        }
    }

    private val _state = MutableLiveData(SignInSecondState(isLoading = false))

    val state: LiveData<SignInSecondState> = _state

    private fun onNumberClick(list: List<Int>) {
        _state.value = _state.value?.copy(
            numbers = list.shuffled()
        )
    }
}