package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetRunningAmountUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(amount: Int) {
        repository.setRunningAmount(amount)
    }
}