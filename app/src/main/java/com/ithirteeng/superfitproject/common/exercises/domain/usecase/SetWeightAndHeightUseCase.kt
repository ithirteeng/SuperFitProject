package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetWeightAndHeightUseCase(
    private val repository: ExerciseRepository,
) {
    operator fun invoke(weight: Number, height: Number) {
        repository.setWeightAndHeight(weight, height)
    }
}