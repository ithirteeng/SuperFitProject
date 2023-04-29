package com.ithirteeng.superfitproject.signin.presentation.second

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInSecondScreenViewModel : ViewModel() {

    fun accept(signInSecondEvent: SignInSecondEvent) {
        when (signInSecondEvent) {
            is SignInSecondEvent.NumberButtonClick -> {
                onNumberClick(list = signInSecondEvent.list, number = signInSecondEvent.number)
            }

            else -> {}
        }
    }

    private val _state = MutableLiveData(SignInSecondState(isLoading = false))

    val state: LiveData<SignInSecondState> = _state

    private fun onNumberClick(list: List<Int>, number: String) {
        var password = getPassword(number)
        if (password.length == 4) {
            doOnPasswordFilled(password = password)
            password = ""
        }
        _state.value = _state.value?.copy(
            numbers = list.shuffled(),
            password = password
        )
    }

    private fun getPassword(number: String): String {
        val password = _state.value?.password
        return if (password != null) {
            password + number
        } else {
            ""
        }
    }

    private fun doOnPasswordFilled(password: String) {
        Log.d("PASSWORD", password)
        //todo send password // change state
    }
}