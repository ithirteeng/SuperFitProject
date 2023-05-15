package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class GetSquatsAmountUseCase(
    private val repository: ExerciseRepository,
) {
    operator fun invoke(): Int =
        repository.getSquatsAmount()
}