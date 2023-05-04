package com.ithirteeng.superfitproject.common.exercises.data.storage

import android.content.Context
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType

class ExerciseStorage(context: Context) {

    private companion object {
        const val STORAGE_NAME = "EXERCISE_STORAGE_NAME"
        const val FIRST_EXERCISE_KEY = "FIRST_EXERCISE_KEY"
        const val SECOND_EXERCISE_KEY = "SECOND_EXERCISE_KEY"
    }

    private val sharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

    fun clearStorage() {
        sharedPreferences.edit().clear().apply()
    }

    fun addExercise(exerciseType: ExerciseType) {
        val firstExercise = sharedPreferences.getString(FIRST_EXERCISE_KEY, null)
        sharedPreferences.edit()
            .putString(SECOND_EXERCISE_KEY, firstExercise)
            .putString(FIRST_EXERCISE_KEY, exerciseType.type)
            .apply()
    }

    fun getExercises(): List<String?> {
        return listOf(
            sharedPreferences.getString(FIRST_EXERCISE_KEY, null),
            sharedPreferences.getString(SECOND_EXERCISE_KEY, null)
        )
    }

}