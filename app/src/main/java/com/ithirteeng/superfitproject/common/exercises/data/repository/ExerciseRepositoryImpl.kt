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

    override fun setWeight(weight: Number) {
        storage.setWeight(weight = weight)
    }

    override fun setHeight(height: Number) {
        storage.setHeight(height = height)
    }

    override fun getWeightAndHeight(): Pair<String?, String?> {
        return storage.getWeightAndHeight()
    }

    override fun setCrunchesAmount(amount: Int) {
        storage.setCrunchesAmount(amount)
    }

    override fun getCrunchesAmount(): Int {
        return storage.getCrunchesAmount()
    }

    override fun setPlankAmount(amount: Int) {
        storage.setPlankAmount(amount)
    }

    override fun getPlankAmount(): Int {
        return storage.getPlankAmount()
    }

    override fun setSquatsAmount(amount: Int) {
        storage.setSquatsAmount(amount)
    }

    override fun getSquatsAmount(): Int {
        return storage.getSquatsAmount()
    }

    override fun setPushUpsAmount(amount: Int) {
        storage.setPushUpsAmount(amount)
    }

    override fun getPushUpsAmount(): Int {
        return storage.getPushUpsAmount()
    }

    override fun setRunningAmount(amount: Int) {
        storage.setRunningAmount(amount)
    }

    override fun getRunningAmount(): Int {
        return storage.getRunningAmount()
    }
}