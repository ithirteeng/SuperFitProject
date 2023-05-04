package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class ClearExerciseStorageUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke() {
        repository.clearStorage()
    }
}