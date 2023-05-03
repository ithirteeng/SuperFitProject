package com.ithirteeng.superfitproject.main.presentation.model

import com.ithirteeng.superfitproject.common.entity.ExerciseEntity

data class MainScreenData(
    val weight: Double? = null,
    val height: Double? = null,
    val exercises: List<ExerciseEntity> = listOf(),
)