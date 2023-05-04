package com.ithirteeng.superfitproject.main.presentation.model

import com.ithirteeng.superfitproject.common.entity.ExerciseEntity

data class MainScreenData(
    val weight: String = "",
    val height: String = "",
    val exercises: List<ExerciseEntity> = listOf(),
)