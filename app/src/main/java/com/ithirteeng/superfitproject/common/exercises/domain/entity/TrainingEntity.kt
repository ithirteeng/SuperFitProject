package com.ithirteeng.superfitproject.common.exercises.domain.entity

import com.google.gson.annotations.SerializedName

data class TrainingEntity(
    @SerializedName("date")
    val date: String,
    @SerializedName("exercise")
    val exercise: String,
    @SerializedName("repeatCount")
    val repeatCount: Int,
)
