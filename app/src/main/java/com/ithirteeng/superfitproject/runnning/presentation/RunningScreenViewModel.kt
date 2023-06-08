package com.ithirteeng.superfitproject.runnning.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.AddExerciseUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetRunningAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SaveTrainingUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetRunningAmountUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

class RunningScreenViewModel(
    private val setRunningAmountUseCase: SetRunningAmountUseCase,
    private val getRunningAmountUseCase: GetRunningAmountUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val getWeightAndHeightUseCase: GetWeightAndHeightUseCase,
) : ViewModel() {

    fun accept(intent: RunningIntent) {
        when (intent) {
            is RunningIntent.ActionIntent -> onAction(intent.steps)
            is RunningIntent.DismissErrorDialog -> TODO()
            is RunningIntent.FinishButtonClick -> TODO()
            is RunningIntent.Initial -> initState()
            is RunningIntent.SetDefaultSteps -> setDefaultSteps(intent.steps)
        }
    }

    private val _state = MutableStateFlow(RunningState())
    val state: StateFlow<RunningState> = _state

    private fun initState() {
        _state.value = RunningState(
            error = null,
            isLoading = false,
            isFinishedUnsuccessfully = false,
            isFinishedSuccessfully = false,
            totalAmount = getRunningAmountUseCase(),
            currentAmount = getRunningAmountUseCase()
        )
    }

    private fun setDefaultSteps(steps: Int) {
        _state.value = _state.value.copy(
            baseSteps = steps
        )
    }

    private fun onAction(steps: Int) {
        val heightString = getWeightAndHeightUseCase().second
        var height = 165
        if (heightString != null) {
            height = heightString.split(" ")[0].toInt()
        }
        val stepSize = height * 0.55 * 0.01

        val passedSteps = steps - _state.value.baseSteps
        val passedMeters = passedSteps * stepSize
        val amount = _state.value.currentAmount - passedMeters

        Log.d("TTTTT", passedMeters.toString())

        if (_state.value.currentAmount - passedMeters <= 0) {
            //todo save training and exit
        } else {
            _state.value = _state.value.copy(
                currentAmount = amount.toInt(),
                currentSteps = steps,
                baseSteps = steps
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveTraining() {
        if (_state.value.currentAmount == _state.value.totalAmount) {
            _state.value = _state.value.copy(
                isLoading = false,
                isFinishedUnsuccessfully = true
            )
        } else {
            viewModelScope.launch {
                saveTrainingUseCase(
                    TrainingEntity(
                        date = SimpleDateFormat("yyyy-MM-dd").format(Date()),
                        exercise = ExerciseType.RUNNING.type,
                        repeatCount = _state.value.totalAmount - _state.value.currentAmount
                    )
                ).onSuccess {
                    addExerciseUseCase(ExerciseType.RUNNING)

                }.onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorHelper.setupErrorEntity(it)
                    )
                }
            }


            _state.value = _state.value.copy(
                isLoading = false,
                isFinishedSuccessfully = true
            )
        }
    }


}