package com.ithirteeng.superfitproject.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _state = MutableLiveData<SplashState>()

    val state: LiveData<SplashState> = _state

    fun accept(splashEvent: SplashEvent) {
        when (splashEvent) {
            is SplashEvent.CheckDataEvent -> checkUserData()
        }

    }

    private fun checkUserData() {
        _state.postValue(SplashState.CompleteCheck)
    }

    private fun testLoading() {
        _state.postValue(SplashState.Loading)
        viewModelScope.launch {
            delay(5000)
            _state.postValue(SplashState.CompleteLoading)
        }
    }
}