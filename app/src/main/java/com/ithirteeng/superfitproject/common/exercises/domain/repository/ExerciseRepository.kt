package com.ithirteeng.superfitproject.common.exercises.domain.repository

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType

interface ExerciseRepository {

    fun addExercise(exerciseType: ExerciseType)

    fun getExercisesList(): List<String?>

    fun clearStorage()

    fun setWeight(weight: Number)

    fun setHeight(height: Number)

    fun getWeightAndHeight(): Pair<String?, String?>
}