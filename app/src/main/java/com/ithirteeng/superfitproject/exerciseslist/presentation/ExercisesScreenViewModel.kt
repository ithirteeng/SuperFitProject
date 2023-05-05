package com.ithirteeng.superfitproject.exerciseslist.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType.CRUNCH
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType.PLANK
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType.PUSH_UP
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType.RUNNING
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType.SQUATS
import com.ithirteeng.superfitproject.common.exercises.domain.mapper.mapExerciseTypeToEntity
import com.ithirteeng.superfitproject.exerciseslist.presentation.model.CompletionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExercisesScreenViewModel : ViewModel() {

    fun accept(exercisesScreenIntent: ExercisesScreenIntent) {
        when (exercisesScreenIntent) {
            is ExercisesScreenIntent.BackButtonClick -> onBackButtonClick()
            is ExercisesScreenIntent.ExerciseClick -> onExerciseClick(
                exercisesScreenIntent.exerciseEntity
            )
            is ExercisesScreenIntent.Initial -> initState()
        }
    }

    private val _state = MutableStateFlow(ExercisesScreenState())

    val state: StateFlow<ExercisesScreenState> = _state

    private fun initState() {
        _state.value = ExercisesScreenState(
            isLoading = false,
            error = null,
            exercisesList = getExercisesList(),
            completionModel = CompletionModel()
        )
    }

    private fun getExercisesList(): List<ExerciseEntity> {
        val listOfTypes = listOf(PUSH_UP, PLANK, SQUATS, CRUNCH, RUNNING)
        val resulList = arrayListOf<ExerciseEntity>()

        for (type in listOfTypes) {
            resulList.add(mapExerciseTypeToEntity(type.type))
        }

        return resulList
    }

    private fun onBackButtonClick() {
        _state.value = _state.value.copy(
            completionModel = CompletionModel(ifBackButtonClicked = true)
        )
    }

    private fun onExerciseClick(exerciseEntity: ExerciseEntity) {
        _state.value = _state.value.copy(
            completionModel = CompletionModel(exerciseClicked = exerciseEntity)
        )
    }

}