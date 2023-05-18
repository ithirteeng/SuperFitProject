package com.ithirteeng.superfitproject.success.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SuccessScreenViewModel : ViewModel() {
    fun accept(intent: SuccessIntent) {
        when (intent) {
            SuccessIntent.GoHomeButtonClick -> _state.value = _state.value.copy(isFinished = true)
            SuccessIntent.Initial -> _state.value = SuccessState()
        }
    }

    private val _state = MutableStateFlow(SuccessState())

    val state: StateFlow<SuccessState> = _state

}