package com.ithirteeng.superfitproject.mybody.domain.entity

import com.google.gson.annotations.SerializedName

data class BodyParamsEntity(
    @SerializedName("weight")
    val weight: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("date")
    val date: String,
)
