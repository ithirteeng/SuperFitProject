package com.ithirteeng.superfitproject.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.mapper.mapExerciseTypeToEntity
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.AddExerciseUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetExercisesUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.main.presentation.model.MainScreenData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val application: Application,
    private val getExercisesUseCase: GetExercisesUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val getWeightAndHeightUseCase: GetWeightAndHeightUseCase,
) : AndroidViewModel(application) {

    fun accept(mainScreenIntent: MainScreenIntent) {
        when (mainScreenIntent) {
            is MainScreenIntent.DetailsButtonClick -> {}
            is MainScreenIntent.ExerciseButtonClick -> {}
            is MainScreenIntent.Initial -> initState()
            is MainScreenIntent.SeeAllButtonClick -> {}
            is MainScreenIntent.SignOutButtonClick -> {}
        }
    }

    private val _state = MutableStateFlow(MainScreenState())

    val state: StateFlow<MainScreenState> = _state

    private fun initState() {
        _state.value = MainScreenState(
            isLoading = false,
            error = null,
            data = MainScreenData(
                weight = getWeightAndHeight().first,
                height = getWeightAndHeight().second,
                exercises = getExercisesList()
            )
        )
    }

    private fun getExercisesList(): List<ExerciseEntity> {
        val list = getExercisesUseCase()
        return if (list[0] == null) {
            addExerciseUseCase(ExerciseType.PLANK)
            addExerciseUseCase(ExerciseType.PUSH_UP)
            listOf(
                mapExerciseTypeToEntity(ExerciseType.PLANK.type),
                mapExerciseTypeToEntity(ExerciseType.PUSH_UP.type)
            )
        } else {
            listOf(
                mapExerciseTypeToEntity(list[0].toString()),
                mapExerciseTypeToEntity(list[1].toString())
            )
        }

    }

    private fun getWeightAndHeight(): Pair<String, String> {
        val weightAndHeight = getWeightAndHeightUseCase()
        var first = weightAndHeight.first
        var second = weightAndHeight.second
        if (first == null) first = application.resources.getString(R.string.undefined)
        if (second == null) second = application.resources.getString(R.string.undefined)
        return Pair(first, second)
    }


}