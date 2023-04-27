package com.ithirteeng.superfitproject.signin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInScreenViewModel : ViewModel() {

    fun accept(signInEvent: SignInEvent) {

    }

    private val _state = MutableLiveData<SignInState>()

    val state: LiveData<SignInState> = _state
}