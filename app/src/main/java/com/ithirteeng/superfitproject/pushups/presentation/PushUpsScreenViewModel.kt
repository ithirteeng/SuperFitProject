package com.ithirteeng.superfitproject.pushups.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.AddExerciseUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetPushUpsAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SaveTrainingUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetPushUpsAmountUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class PushUpsScreenViewModel(
    private val getPushUpsAmountUseCase: GetPushUpsAmountUseCase,
    private val setPushUpsAmountUseCase: SetPushUpsAmountUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
) : ViewModel() {

    fun accept(intent: PushUpsIntent) {
        when (intent) {
            PushUpsIntent.ActionIntent -> onAction()
            PushUpsIntent.DismissErrorDialog -> onDismissError()
            PushUpsIntent.FinishButtonClick -> onFinishButtonClick()
            PushUpsIntent.Initial -> initState()
        }
    }

    private val _state = MutableStateFlow(PushUpsState())

    val state: StateFlow<PushUpsState> = _state

    private fun initState() {
        _state.value = PushUpsState(
            error = null,
            isLoading = false,
            totalAmount = getExercisesAmount(),
            currentAmount = getExercisesAmount(),
        )
    }

    private fun getExercisesAmount(): Int {
        var amount = getPushUpsAmountUseCase()
        if (amount < 10) {
            amount = 10
        }
        return amount
    }

    private fun onAction() {
        _state.value = _state.value.copy(
            currentAmount = _state.value.currentAmount - 1
        )
        if (_state.value.currentAmount == 0) {
            saveTrainings()
        }
    }

    private fun onDismissError() {
        _state.value = _state.value.copy(
            error = null
        )
        saveTrainings()
    }

    private fun onFinishButtonClick() {
        saveTrainings()
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveTrainings() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        if (_state.value.currentAmount != _state.value.totalAmount) {
            viewModelScope.launch {
                saveTrainingUseCase(
                    TrainingEntity(
                        date = SimpleDateFormat("yyyy-MM-dd").format(Date()),
                        exercise = ExerciseType.PLANK.type,
                        repeatCount = _state.value.totalAmount - _state.value.currentAmount
                    )
                ).onSuccess {
                    addExerciseUseCase(ExerciseType.PUSH_UP)
                    if (_state.value.currentAmount == 0) {
                        setPushUpsAmountUseCase(_state.value.totalAmount + 5)
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isFinishedSuccessfully = true
                        )
                    } else {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isFinishedUnsuccessfully = true
                        )
                    }

                }.onFailure {
                    _state.value = _state.value.copy(
                        error = ErrorHelper.setupErrorEntity(it)
                    )
                }
            }
        } else {
            _state.value = _state.value.copy(
                isLoading = false,
                isFinishedUnsuccessfully = true
            )
        }
    }
}