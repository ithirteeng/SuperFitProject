package com.ithirteeng.superfitproject.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetCurrentUserNameUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetTokenFromLocalStorageUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetUserEntryFlagUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.SetUserEntryFlagUseCase
import com.ithirteeng.superfitproject.splash.presentation.model.CompletionModel
import com.ithirteeng.superfitproject.splash.presentation.model.SplashNextScreenType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val getCurrentUserNameUseCase: GetCurrentUserNameUseCase,
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val getUserEntryFlagUseCase: GetUserEntryFlagUseCase,
    private val setUserEntryFlagUseCase: SetUserEntryFlagUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())

    val state: StateFlow<SplashState> = _state

    fun accept(splashScreenIntent: SplashScreenIntent) {
        when (splashScreenIntent) {
            is SplashScreenIntent.CheckDataScreenIntent -> checkUserData()
        }

    }

    private fun checkUserData() {
        _state.value = SplashState()
        viewModelScope.launch {
            delay(1000)
            if (getUserEntryFlagUseCase()) {
                onRepeatEntry(getCurrentUserNameUseCase())
            } else {
                onFirstEntry()
            }

        }

    }

    private fun onFirstEntry() {
        _state.value = _state.value.copy(
            completionModel = CompletionModel(
                true,
                SplashNextScreenType.REGISTRATION
            ),
        )
        setUserEntryFlagUseCase(true)
    }

    private fun onRepeatEntry(userName: String?) {
        if (!checkTokenExisting()) {
            if (userName == null) {
                _state.value = _state.value.copy(
                    completionModel = CompletionModel(
                        true,
                        SplashNextScreenType.LOGIN
                    )
                )
            } else {
                _state.value = _state.value.copy(
                    completionModel = CompletionModel(
                        true,
                        SplashNextScreenType.LOGIN2
                    ),
                    userEmail = userName
                )
            }
        } else {
            _state.value = _state.value.copy(
                completionModel = CompletionModel(
                    true,
                    SplashNextScreenType.MAIN
                ),
                userEmail = userName
            )
        }
    }

    private fun checkTokenExisting(): Boolean {
        val token = getTokenFromLocalStorageUseCase()
        return token != null
    }
}