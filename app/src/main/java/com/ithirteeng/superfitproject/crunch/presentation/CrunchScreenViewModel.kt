package com.ithirteeng.superfitproject.crunch.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CrunchScreenViewModel() : ViewModel() {

    fun accept(intent: CrunchScreenIntent) {
        when (intent) {
            CrunchScreenIntent.FinishButtonClick -> {}
            CrunchScreenIntent.Initial -> {}
        }
    }

    private val _state = MutableStateFlow(CrunchScreenState())

    val state: StateFlow<CrunchScreenState> = _state

    private fun initState() {
        _state.value = CrunchScreenState(

        )
    }

}