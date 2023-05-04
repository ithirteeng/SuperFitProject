package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class GetWeightAndHeightUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(): Pair<String?, String?> {
        return repository.getWeightAndHeight()
    }
}