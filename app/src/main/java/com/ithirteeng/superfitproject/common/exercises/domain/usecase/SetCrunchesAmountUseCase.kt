package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class SetCrunchesAmountUseCase(
    private val repository: ExerciseRepository,
) {
    operator fun invoke(crunchesAmount: Int) {
        repository.setCrunchesAmount(crunchesAmount)
    }
}