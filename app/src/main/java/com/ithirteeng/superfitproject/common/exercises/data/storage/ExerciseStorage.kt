package com.ithirteeng.superfitproject.common.exercises.data.storage

import android.content.Context
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType

class ExerciseStorage(context: Context) {

    private companion object {
        const val STORAGE_NAME = "EXERCISE_STORAGE_NAME"
        const val FIRST_EXERCISE_KEY = "FIRST_EXERCISE_KEY"
        const val SECOND_EXERCISE_KEY = "SECOND_EXERCISE_KEY"
        const val WEIGHT_KEY = "WEIGHT_KEY"
        const val HEIGHT_KEY = "HEIGHT_KEY"

        const val CRUNCH_AMOUNT_KEY = "CRUNCH_AMOUNT_KEY"
        const val SQUATS_AMOUNT_KEY = "SQUATS_AMOUNT_KEY"
        const val PLANK_AMOUNT_KEY = "PLANK_AMOUNT_KEY"
        const val PUSH_UPS_AMOUNT_KEY = "PUSH_UPS_AMOUNT_KEY"
        const val RUNNING_AMOUNT_KEY = "RUNNING_AMOUNT_KEY"
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

    fun setWeight(weight: Number) {
        sharedPreferences.edit()
            .putString(WEIGHT_KEY, "$weight kg")
            .apply()
    }

    fun setHeight(height: Number) {
        sharedPreferences.edit()
            .putString(HEIGHT_KEY, "$height cm")
            .apply()
    }

    fun getWeightAndHeight(): Pair<String?, String?> {
        return Pair(
            sharedPreferences.getString(WEIGHT_KEY, null),
            sharedPreferences.getString(HEIGHT_KEY, null)
        )
    }


    fun setCrunchesAmount(crunchesAmount: Int) {
        sharedPreferences.edit()
            .putInt(CRUNCH_AMOUNT_KEY, crunchesAmount)
            .apply()
    }

    fun getCrunchesAmount(): Int {
        return sharedPreferences.getInt(CRUNCH_AMOUNT_KEY, 10)
    }


    fun setPlankAmount(plankAmount: Int) {
        sharedPreferences.edit()
            .putInt(PLANK_AMOUNT_KEY, plankAmount)
            .apply()
    }

    fun getPlankAmount(): Int {
        return sharedPreferences.getInt(PLANK_AMOUNT_KEY, 0)
    }


    fun setSquatsAmount(squatsAmount: Int) {
        sharedPreferences.edit()
            .putInt(SQUATS_AMOUNT_KEY, squatsAmount)
            .apply()
    }

    fun getSquatsAmount(): Int {
        return sharedPreferences.getInt(SQUATS_AMOUNT_KEY, 10)
    }


    fun setPushUpsAmount(pushUpsAmount: Int) {
        sharedPreferences.edit()
            .putInt(PUSH_UPS_AMOUNT_KEY, pushUpsAmount)
            .apply()
    }

    fun getPushUpsAmount(): Int {
        return sharedPreferences.getInt(PUSH_UPS_AMOUNT_KEY, 10)
    }


    fun setRunningAmount(runningAmount: Int) {
        sharedPreferences.edit()
            .putInt(RUNNING_AMOUNT_KEY, runningAmount)
            .apply()
    }

    fun getRunningAmount(): Int {
        return sharedPreferences.getInt(RUNNING_AMOUNT_KEY, 1000)
    }


}