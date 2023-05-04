package com.ithirteeng.superfitproject.common.exercises.data.repository

import com.ithirteeng.superfitproject.common.exercises.data.storage.ExerciseStorage
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl(
    private val storage: ExerciseStorage,
) : ExerciseRepository {
    override fun addExercise(exerciseType: ExerciseType) {
        storage.addExercise(exerciseType = exerciseType)
    }

    override fun getExercisesList(): List<String?> {
        return storage.getExercises()
    }

    override fun clearStorage() {
        storage.clearStorage()
    }

    override fun setWeightAndHeight(weight: Number, height: Number) {
        storage.setWeightAndHeight(weight, height)
    }

    override fun getWeightAndHeight(): Pair<String?, String?> {
        return storage.getWeightAndHeight()
    }
}