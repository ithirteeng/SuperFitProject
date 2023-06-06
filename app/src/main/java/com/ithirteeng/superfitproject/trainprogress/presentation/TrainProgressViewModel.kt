package com.ithirteeng.superfitproject.trainprogress.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetTrainingsUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import com.ithirteeng.superfitproject.common.utils.byDateOrderComparatorTrainingEntity
import com.ithirteeng.superfitproject.trainprogress.presentation.model.ProgressIndicator
import com.ithirteeng.superfitproject.trainprogress.presentation.model.TrainStatModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrainProgressViewModel(
    private val application: Application,
    private val getTrainingsUseCase: GetTrainingsUseCase,
) : AndroidViewModel(application) {

    fun accept(intent: TrainProgressIntent) {
        when (intent) {
            TrainProgressIntent.BackButtonClick -> onBackButtonClick()
            TrainProgressIntent.Initial -> initState()
            TrainProgressIntent.DismissError -> dismissError()
        }
    }

    private val _state = MutableStateFlow(TrainProgressState())

    val state: StateFlow<TrainProgressState> = _state

    private fun initState() {
        _state.value = TrainProgressState(
            isLoading = true,
            error = null,
            ifBackButtonPressed = false
        )
        getTrainings()
    }

    private fun dismissError() {
        _state.value = _state.value.copy(
            error = null,
            isLoading = false
        )
    }

    private fun onBackButtonClick() {
        _state.value = _state.value.copy(
            isLoading = false,
            ifBackButtonPressed = true
        )
    }

    private fun getTrainings() {
        viewModelScope.launch {
            getTrainingsUseCase()
                .onSuccess { defaultList ->
                    val list = defaultList.sortedWith(byDateOrderComparatorTrainingEntity)
                    _state.value = _state.value.copy(
                        pushUpsTrain = setupTrainStats(list, ExerciseType.PUSH_UP),
                        crunchTrain = setupTrainStats(list, ExerciseType.CRUNCH),
                        squatsTrain = setupTrainStats(list, ExerciseType.SQUATS),
                        runningTrain = setupTrainStats(list, ExerciseType.RUNNING),
                        plankTrain = setupTrainStats(list, ExerciseType.PLANK),
                        isLoading = false
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

    private fun setupTrainStats(
        list: List<TrainingEntity>,
        exerciseType: ExerciseType,
    ): TrainStatModel {
        val exerciseStatsList = list.filter { entity -> entity.exercise == exerciseType.type }
        val amountEnding = getCorrectEnding(exerciseType = exerciseType)

        if (exerciseStatsList.isEmpty()) {
            return TrainStatModel(
                amount = " —",
                progress = " —",
                indicator = ProgressIndicator.NULL
            )
        } else if (exerciseStatsList.size == 1) {
            return TrainStatModel(
                amount = " ${exerciseStatsList.first().repeatCount} $amountEnding",
                progress = " —",
                indicator = ProgressIndicator.NULL
            )
        } else {
            val percentage = getPercentage(
                exerciseStatsList.first().repeatCount,
                exerciseStatsList[1].repeatCount
            )
            return TrainStatModel(
                amount = " ${exerciseStatsList.first().repeatCount} $amountEnding",
                progress = " $percentage%",
                indicator = getIndicator(percentage)
            )
        }
    }

    private fun getIndicator(percentage: Int): ProgressIndicator {
        return if (percentage < 0) {
            ProgressIndicator.DESCENDING
        } else if (percentage > 0) {
            ProgressIndicator.ASCENDING
        } else {
            ProgressIndicator.NULL
        }
    }

    private fun getPercentage(lastAmount: Int, previousAmount: Int): Int {
        return ((lastAmount - previousAmount) / previousAmount.toFloat() * 100).toInt()
    }

    private fun getCorrectEnding(exerciseType: ExerciseType): String {
        return when (exerciseType) {
            ExerciseType.CRUNCH -> application.getString(R.string.times)
            ExerciseType.SQUATS -> application.getString(R.string.times)
            ExerciseType.PUSH_UP -> application.getString(R.string.times)
            ExerciseType.PLANK -> application.getString(R.string.seconds)
            ExerciseType.RUNNING -> application.getString(R.string.meters)
        }
    }
}