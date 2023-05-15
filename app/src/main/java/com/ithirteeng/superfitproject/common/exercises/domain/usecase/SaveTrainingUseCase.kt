package com.ithirteeng.superfitproject.common.exercises.domain.usecase

import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository
import com.ithirteeng.superfitproject.common.utils.provideResult

class SaveTrainingUseCase(
    private val repository: ExerciseRepository,
) {
    suspend operator fun invoke(trainingEntity: TrainingEntity): Result<Unit> {
        return provideResult {
            repository.saveTraining(trainingEntity)
        }
    }
}