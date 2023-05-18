package com.ithirteeng.superfitproject.plank.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetPlankAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetPlankAmountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlankScreenViewModel(
    private val getPlankAmountUseCase: GetPlankAmountUseCase,
    private val setPlankAmountUseCase: SetPlankAmountUseCase,
) : ViewModel() {

    fun accept(intent: PlankIntent) {
        when (intent) {
            PlankIntent.Initial -> initState()
            PlankIntent.BackButtonClick -> exitFromScreen()
            PlankIntent.FinishButtonClick -> exitFromScreen()
            PlankIntent.DismissErrorDialog -> dismissErrorDialog()
            PlankIntent.GoButtonClick -> startExercise()
            PlankIntent.LaterButtonClick -> exitFromScreen()
        }
    }

    private val _state = MutableStateFlow(PlankState())

    val state: StateFlow<PlankState> = _state

    private fun initState() {
        _state.value = PlankState(
            error = null,
            isLoading = false,
            isFinishedSuccessfully = false,
            totalTime = getTotalTime(),
            isTimerRunning = false,
            isStartDialogOpened = true
        )
    }

    private fun getTotalTime(): Int {
        var totalTime = getPlankAmountUseCase()
        if (totalTime < 20) {
            totalTime = 20
        }
        return totalTime
    }

    private fun exitFromScreen() {
        _state.value = _state.value.copy(
            isTimerRunning = false,
            isFinishedUnsuccessfully = true,
            isStartDialogOpened = false
        )
    }

    private fun dismissErrorDialog() {
        _state.value = _state.value.copy(
            error = null
        )
    }

    private fun startExercise() {
        _state.value = _state.value.copy(
            isTimerRunning = true,
            isStartDialogOpened = false
        )
    }
}