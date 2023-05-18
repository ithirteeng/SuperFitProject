package com.ithirteeng.superfitproject.plank.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlankScreenViewModel : ViewModel() {

    fun accept(intent: PlankIntent) {
        when (intent) {
            PlankIntent.Initial -> _state.value = PlankState()
            PlankIntent.BackButtonClick -> TODO()
            PlankIntent.FinishButtonClick -> TODO()
            PlankIntent.DismissErrorDialog -> TODO()
        }
    }

    private val _state = MutableStateFlow(PlankState())

    val state: StateFlow<PlankState> = _state
}