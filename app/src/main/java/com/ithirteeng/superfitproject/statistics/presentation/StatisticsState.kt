package com.ithirteeng.superfitproject.statistics.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.statistics.domain.entity.BodyParamsEntity

data class StatisticsState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val selectedType: ExerciseType = ExerciseType.PUSH_UP,
    val ifBackButtonPressed: Boolean = false,

    val paramsData: List<BodyParamsEntity> = listOf(),
    val pushUpsHistory: List<TrainingEntity> = listOf(),
    val plankHistory: List<TrainingEntity> = listOf(),
    val crunchHistory: List<TrainingEntity> = listOf(),
    val squatsHistory: List<TrainingEntity> = listOf(),
    val runningHistory: List<TrainingEntity> = listOf(),
)
