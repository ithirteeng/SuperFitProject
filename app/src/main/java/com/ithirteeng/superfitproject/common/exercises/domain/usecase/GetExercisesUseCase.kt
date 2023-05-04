package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class GetExercisesUseCase(
    private val repository: ExerciseRepository,
) {
    operator fun invoke(): List<String?> {
        return repository.getExercisesList()
    }
}