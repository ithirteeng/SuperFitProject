package com.ithirteeng.superfitproject.common.entity

import com.ithirteeng.superfitproject.common.model.ExerciseType

data class ExerciseEntity(
    val name: String,
    val descriptionId: Int,
    val type: ExerciseType,
)
