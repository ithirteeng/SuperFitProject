package com.ithirteeng.superfitproject.common.entity

data class ErrorEntity(
    val exception: Exception,
    val messageId: Int,
    val errorCode: Int? = null,
)
