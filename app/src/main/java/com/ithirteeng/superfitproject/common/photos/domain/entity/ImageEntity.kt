package com.ithirteeng.superfitproject.common.photos.domain.entity

import com.google.gson.annotations.SerializedName

data class ImageEntity(
    val id: String,
    @SerializedName("uploaded")
    val uploadTime: Int,
)