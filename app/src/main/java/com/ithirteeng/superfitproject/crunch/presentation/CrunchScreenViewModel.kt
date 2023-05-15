package com.ithirteeng.superfitproject.crunch.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetCrunchesAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetCrunchesAmountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CrunchScreenViewModel(
    private val setCrunchesAmountUseCase: SetCrunchesAmountUseCase,
    private val getCrunchesAmountUseCase: GetCrunchesAmountUseCase,
) : ViewModel() {

    fun accept(intent: CrunchScreenIntent) {
        when (intent) {
            CrunchScreenIntent.FinishButtonClick -> onFinishButtonClick()
            CrunchScreenIntent.Initial -> initState()
        }
    }

    private val _state = MutableStateFlow(CrunchScreenState())

    val state: StateFlow<CrunchScreenState> = _state

    private fun initState() {
        _state.value = CrunchScreenState(
            crunchesAmount = getCrunchesAmountUseCase()
        )
    }

    private fun onFinishButtonClick() {
        setCrunchesAmountUseCase(_state.value.crunchesAmount + 5)
        _state.value = _state.value.copy(isFinished = true)
    }

}