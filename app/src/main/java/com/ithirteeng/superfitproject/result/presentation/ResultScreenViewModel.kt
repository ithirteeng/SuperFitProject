package com.ithirteeng.superfitproject.result.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultScreenViewModel : ViewModel() {
    fun accept(intent: ResultIntent) {
        when (intent) {
            ResultIntent.GoHomeButtonClick -> _state.value = _state.value.copy(isFinished = true)
            ResultIntent.Initial -> _state.value = ResultState()
        }
    }

    private val _state = MutableStateFlow(ResultState())

    val state: StateFlow<ResultState> = _state

}