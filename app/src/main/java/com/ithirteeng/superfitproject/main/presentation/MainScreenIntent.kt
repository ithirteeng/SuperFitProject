package com.ithirteeng.superfitproject.main.presentation

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity

sealed class MainScreenIntent {
    object Initial : MainScreenIntent()
    object SignOutButtonClick : MainScreenIntent()
    object DetailsButtonClick : MainScreenIntent()
    object SeeAllButtonClick : MainScreenIntent()
    class ExerciseButtonClick(exerciseEntity: ExerciseEntity) : MainScreenIntent()
}
