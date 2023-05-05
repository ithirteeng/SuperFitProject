package com.ithirteeng.superfitproject.exerciseslist.presentation

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity

sealed class ExercisesScreenIntent {
    object BackButtonClick : ExercisesScreenIntent()
    class ExerciseClick(val exerciseEntity: ExerciseEntity) : ExercisesScreenIntent()
    object Initial : ExercisesScreenIntent()
}
