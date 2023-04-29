package com.ithirteeng.superfitproject.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetCurrentUserNameUseCase
import com.ithirteeng.superfitproject.splash.presentation.model.CompletionModel
import com.ithirteeng.superfitproject.splash.presentation.model.SplashNextScreenType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val getCurrentUserNameUseCase: GetCurrentUserNameUseCase,
) : ViewModel() {

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
            val userName = getCurrentUserNameUseCase()
            if (userName == null) {
                _state.value = _state.value?.copy(
                    completionModel = CompletionModel(
                        true,
                        SplashNextScreenType.LOGIN
                    )
                )
            } else {
                _state.value = _state.value?.copy(
                    completionModel = CompletionModel(
                        true,
                        SplashNextScreenType.LOGIN2
                    ),
                    userEmail = userName
                )
            }
        }

    }
}