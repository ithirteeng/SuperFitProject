package com.ithirteeng.superfitproject.main.presentation.model

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity

data class CompletionModel(
    val ifSeeAllButtonClicked: Boolean = false,
    val ifDetailsButtonClicked: Boolean = false,
    val exerciseClicked: ExerciseEntity? = null,
    val ifSignOutCompleted: Boolean = false
)