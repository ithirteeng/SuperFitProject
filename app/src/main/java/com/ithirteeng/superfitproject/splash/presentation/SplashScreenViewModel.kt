package com.ithirteeng.superfitproject.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.splash.presentation.model.CompletionModel
import com.ithirteeng.superfitproject.splash.presentation.model.SplashNextScreenType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _state = MutableLiveData(SplashState())

    val state: LiveData<SplashState> = _state

    fun accept(splashEvent: SplashEvent) {
        when (splashEvent) {
            is SplashEvent.CheckDataEvent -> checkUserData()
        }

    }

    private fun checkUserData() {
        _state.value = _state.value?.copy(
            isLoading = false
        )
        viewModelScope.launch {
            delay(1000)
            _state.value = _state.value?.copy(
                completionModel = CompletionModel(
                    true,
                    SplashNextScreenType.LOGIN
                )
            )
        }

    }
}