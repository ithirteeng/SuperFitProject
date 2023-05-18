package com.ithirteeng.superfitproject.plank.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetPlankAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SaveTrainingUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetPlankAmountUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class PlankScreenViewModel(
    private val getPlankAmountUseCase: GetPlankAmountUseCase,
    private val setPlankAmountUseCase: SetPlankAmountUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
) : ViewModel() {

    fun accept(intent: PlankIntent) {
        when (intent) {
            PlankIntent.Initial -> initState()
            PlankIntent.BackButtonClick -> exitFromScreen()
            PlankIntent.FinishButtonClick -> exitFromScreen()
            PlankIntent.DismissErrorDialog -> dismissErrorDialog()
            PlankIntent.GoButtonClick -> startExercise()
            PlankIntent.LaterButtonClick -> exitFromScreen()
            PlankIntent.FinishExercise -> finishExercise()
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

    @SuppressLint("SimpleDateFormat")
    private fun finishExercise() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            saveTrainingUseCase(
                TrainingEntity(
                    date = SimpleDateFormat("yyyy-MM-dd").format(Date()),
                    exercise = ExerciseType.PLANK.type,
                    repeatCount = _state.value.totalTime
                )
            ).onSuccess {
                setPlankAmountUseCase(_state.value.totalTime + 5)
                _state.value = _state.value.copy(
                    isLoading = false,
                    isFinishedSuccessfully = true
                )
            }.onFailure {
                _state.value = _state.value.copy(error = ErrorHelper.setupErrorEntity(it))
            }
        }

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