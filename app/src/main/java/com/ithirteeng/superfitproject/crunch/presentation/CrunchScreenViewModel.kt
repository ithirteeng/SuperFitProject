package com.ithirteeng.superfitproject.crunch.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetCrunchesAmountUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SaveTrainingUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetCrunchesAmountUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class CrunchScreenViewModel(
    private val setCrunchesAmountUseCase: SetCrunchesAmountUseCase,
    private val getCrunchesAmountUseCase: GetCrunchesAmountUseCase,
    private val saveTrainingUseCase: SaveTrainingUseCase,
) : ViewModel() {

    fun accept(intent: CrunchScreenIntent) {
        when (intent) {
            CrunchScreenIntent.FinishButtonClick -> onFinishButtonClick()
            CrunchScreenIntent.Initial -> initState()
            CrunchScreenIntent.DismissErrorDialog -> {
                _state.value = _state.value.copy(error = null)
            }
        }
    }

    private val _state = MutableStateFlow(CrunchScreenState())

    val state: StateFlow<CrunchScreenState> = _state

    private fun initState() {
        _state.value = CrunchScreenState(
            crunchesAmount = getCrunchesAmountUseCase()
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun onFinishButtonClick() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            saveTrainingUseCase(
                TrainingEntity(
                    date = SimpleDateFormat("yyyy-MM-dd").format(Date()),
                    exercise = ExerciseType.CRUNCH.type,
                    repeatCount = _state.value.crunchesAmount
                )
            ).onSuccess {
                setCrunchesAmountUseCase(_state.value.crunchesAmount + 5)
                _state.value = _state.value.copy(
                    isFinished = true,
                    isLoading = false
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