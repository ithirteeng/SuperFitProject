package com.ithirteeng.superfitproject.exerciseslist.presentation.model

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity

data class CompletionModel(
    val exerciseClicked: ExerciseEntity? = null,
    val ifBackButtonClicked: Boolean = false,
)
