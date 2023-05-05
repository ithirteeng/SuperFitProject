package com.ithirteeng.superfitproject.exerciseslist.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity
import com.ithirteeng.superfitproject.exerciseslist.presentation.model.CompletionModel

data class ExercisesScreenState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val exercisesList: List<ExerciseEntity> = listOf(),
    val completionModel: CompletionModel = CompletionModel(),
)