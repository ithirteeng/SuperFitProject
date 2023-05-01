package com.ithirteeng.superfitproject.signup.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpScreenViewModel : ViewModel() {

    fun accept(signUpEvent: SignUpEvent) {

    }

    private val _state = MutableLiveData(SignUpState())

    val state: LiveData<SignUpState> = _state


}