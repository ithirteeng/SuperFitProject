package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetPushUpsAmountUseCase(
    private val repository: ExerciseRepository,
) {
    operator fun invoke(amount: Int) {
        repository.setPushUpsAmount(amount)
    }
}