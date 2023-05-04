package com.ithirteeng.superfitproject.common.exercises.domain.mapper

import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType

fun mapExerciseTypeToEntity(exerciseType: String): ExerciseEntity {
    return when (exerciseType) {
        ExerciseType.PUSH_UP.type -> ExerciseEntity(
            R.string.push_ups,
            R.string.push_up_description,
            ExerciseType.PUSH_UP,
            R.drawable.push_ups_image
        )

        ExerciseType.CRUNCH.type -> ExerciseEntity(
            R.string.crunch,
            R.string.crunch_description,
            ExerciseType.CRUNCH,
            R.drawable.crunch_image
        )

        ExerciseType.SQUATS.type -> ExerciseEntity(
            R.string.squats,
            R.string.squats_description,
            ExerciseType.SQUATS,
            R.drawable.squats_image
        )

        ExerciseType.RUNNING.type -> ExerciseEntity(
            R.string.running,
            R.string.running_description,
            ExerciseType.RUNNING,
            R.drawable.running_image
        )

        ExerciseType.PLANK.type -> ExerciseEntity(
            R.string.plank,
            R.string.plank_description,
            ExerciseType.PLANK,
            R.drawable.plank_image
        )

        else -> ExerciseEntity(
            R.string.plank,
            R.string.plank_description,
            ExerciseType.PLANK,
            R.drawable.plank_image
        )
    }
}