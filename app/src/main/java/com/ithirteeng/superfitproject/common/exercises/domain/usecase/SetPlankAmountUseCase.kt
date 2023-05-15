package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetPlankAmountUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(amount: Int) {
        repository.setPlankAmount(amount)
    }
}