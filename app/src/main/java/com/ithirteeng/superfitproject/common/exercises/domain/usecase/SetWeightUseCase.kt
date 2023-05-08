package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetWeightUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(weight: Number) {
        repository.setWeight(weight = weight)
    }
}