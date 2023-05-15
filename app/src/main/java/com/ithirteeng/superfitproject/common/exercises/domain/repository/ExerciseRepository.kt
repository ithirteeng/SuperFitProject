package com.ithirteeng.superfitproject.common.exercises.domain.repository

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity

interface ExerciseRepository {

    fun addExercise(exerciseType: ExerciseType)

    fun getExercisesList(): List<String?>

    fun clearStorage()

    fun setWeight(weight: Number)

    fun setHeight(height: Number)

    fun getWeightAndHeight(): Pair<String?, String?>

    fun setCrunchesAmount(amount: Int)

    fun getCrunchesAmount(): Int

    fun setPlankAmount(amount: Int)

    fun getPlankAmount(): Int

    fun setSquatsAmount(amount: Int)

    fun getSquatsAmount(): Int

    fun setPushUpsAmount(amount: Int)

    fun getPushUpsAmount(): Int

    fun setRunningAmount(amount: Int)

    fun getRunningAmount(): Int

    suspend fun saveTraining(trainingEntity: TrainingEntity)

    suspend fun getTrainings(): List<TrainingEntity>
}