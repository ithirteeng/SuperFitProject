package com.ithirteeng.superfitproject.statistics.presentation

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType

sealed class StatisticsIntent {
    object Initial : StatisticsIntent()
    object BackButtonClick : StatisticsIntent()
    class TrainingTypeButtonClick(val exerciseType: ExerciseType) : StatisticsIntent()
    object DismissError : StatisticsIntent()
}