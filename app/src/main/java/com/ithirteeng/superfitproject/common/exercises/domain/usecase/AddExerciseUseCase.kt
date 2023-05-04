package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class AddExerciseUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(exerciseType: ExerciseType) {
        repository.addExercise(exerciseType = exerciseType)
    }
}