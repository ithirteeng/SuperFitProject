package com.ithirteeng.superfitproject.statistics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetTrainingsUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import com.ithirteeng.superfitproject.statistics.domain.usecase.GetBodyParamsHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val getBodyParamsHistoryUseCase: GetBodyParamsHistoryUseCase,
    private val getTrainingsUseCase: GetTrainingsUseCase,
) : ViewModel() {

    fun accept(intent: StatisticsIntent) {
        when (intent) {
            is StatisticsIntent.BackButtonClick -> onBackButtonClick()
            is StatisticsIntent.Initial -> initState()
            is StatisticsIntent.TrainingTypeButtonClick -> onTrainingButtonClick(intent.exerciseType)
            is StatisticsIntent.DismissError -> onDismissError()
        }
    }

    private val _state = MutableStateFlow(StatisticsState())
    val state: StateFlow<StatisticsState> = _state

    private fun onDismissError() {
        _state.value = _state.value.copy(
            error = null,
            isLoading = false
        )
    }

    private fun onTrainingButtonClick(exerciseType: ExerciseType) {
        _state.value = _state.value.copy(
            selectedType = exerciseType
        )
    }

    private fun onBackButtonClick() {
        _state.value = _state.value.copy(
            isLoading = false,
            ifBackButtonPressed = true
        )
    }

    private fun initState() {
        _state.value = StatisticsState(
            error = null,
            isLoading = false,
        )
        getExercises()
        getBodyParamsHistory()
    }

    private fun getExercises() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            getTrainingsUseCase()
                .onSuccess { list ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        pushUpsList = list.filter { it.exercise == ExerciseType.PUSH_UP.type },
                        plankList = list.filter { it.exercise == ExerciseType.PLANK.type },
                        squatsList = list.filter { it.exercise == ExerciseType.SQUATS.type },
                        runningList = list.filter { it.exercise == ExerciseType.RUNNING.type },
                        crunchList = list.filter { it.exercise == ExerciseType.CRUNCH.type }
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorHelper.setupErrorEntity(it)
                    )
                }
        }
    }

    private fun getBodyParamsHistory() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            getBodyParamsHistoryUseCase()
                .onSuccess {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        paramsData = it
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorHelper.setupErrorEntity(it)
                    )
                }
        }
    }
}