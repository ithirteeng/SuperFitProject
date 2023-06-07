package com.ithirteeng.superfitproject.runnning.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RunningScreenViewModel : ViewModel() {

    fun accept(intent: RunningIntent) {
        when (intent) {
            RunningIntent.ActionIntent -> TODO()
            RunningIntent.DismissErrorDialog -> TODO()
            RunningIntent.FinishButtonClick -> TODO()
            RunningIntent.Initial -> TODO()
        }
    }

    private val _state = MutableStateFlow(RunningState())
    val state: StateFlow<RunningState> = _state

}