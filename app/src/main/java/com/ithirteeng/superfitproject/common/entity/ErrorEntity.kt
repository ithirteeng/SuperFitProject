package com.ithirteeng.superfitproject.common.entity

data class ErrorEntity(
    val exception: Exception,
    val message: String? = null,
    val errorCode: Int? = null,
)
