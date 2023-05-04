package com.ithirteeng.superfitproject.common.exercises.domain.repository

import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType

interface ExerciseRepository {

    fun addExercise(exerciseType: ExerciseType)

    fun getExercisesList(): List<String?>

    fun clearStorage()

    fun setWeightAndHeight(weight: Number, height: Number)

    fun getWeightAndHeight(): Pair<String?, String?>
}