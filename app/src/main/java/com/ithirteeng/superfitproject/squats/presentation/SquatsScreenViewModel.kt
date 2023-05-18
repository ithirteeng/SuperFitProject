package com.ithirteeng.superfitproject.squats.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.AddExerciseUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetSquatsAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SaveTrainingUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetSquatsAmountUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class SquatsScreenViewModel(
    private val getSquatsAmountUseCase: GetSquatsAmountUseCase,
    private val setSquatsAmountUseCase: SetSquatsAmountUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
) : ViewModel() {

    fun accept(intent: SquatsIntent) {
        when (intent) {
            SquatsIntent.ActionIntent -> onAction()
            SquatsIntent.BackButtonClick -> exitFromScreen()
            SquatsIntent.Initial -> initState()
            SquatsIntent.DismissErrorDialog -> dismissError()
        }
    }

    private val _state = MutableStateFlow(SquatsState())

    val state: StateFlow<SquatsState> = _state

    private fun initState() {
        _state.value = SquatsState(
            currentAmount = getExercisesAmount()
        )
    }

    private fun dismissError() {
        _state.value = _state.value.copy(
            error = null,
            isLoading = false
        )
        if (_state.value.currentAmount <= 0) {
            onExercisesDone()
        }
    }

    private fun getExercisesAmount(): Int {
        var amount = getSquatsAmountUseCase()
        if (amount < 10) {
            amount = 10
        }
        return amount
    }

    private fun onAction() {
        val amount = _state.value.currentAmount
        if (amount == 1) {
            _state.value = _state.value.copy(
                currentAmount = 0,
                isLoading = true
            )
            onExercisesDone()
        } else {
            _state.value = _state.value.copy(
                currentAmount = amount - 1
            )
        }
    }

    private fun exitFromScreen() {
        _state.value = _state.value.copy(
            isFinishedUnsuccessfully = true,
            isLoading = false,
            error = null
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun onExercisesDone() {
        _state.value = _state.value.copy(
            isLoading = true,
        )
        viewModelScope.launch {
            saveTrainingUseCase(
                TrainingEntity(
                    date = SimpleDateFormat("yyyy-MM-dd").format(Date()),
                    exercise = ExerciseType.PLANK.type,
                    repeatCount = getSquatsAmountUseCase()
                )
            ).onSuccess {
                addExerciseUseCase(ExerciseType.SQUATS)
                setSquatsAmountUseCase(getSquatsAmountUseCase() + 5)
                _state.value = _state.value.copy(
                    isLoading = false,
                    isFinishedSuccessfully = true
                )
            }.onFailure {
                _state.value = _state.value.copy(
                    error = ErrorHelper.setupErrorEntity(it),
                    isLoading = false
                )
            }
        }
    }
}