package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetHeightUseCase(
    private val repository: ExerciseRepository,
) {
    operator fun invoke(height: Number) {
        repository.setHeight(height = height)
    }
}