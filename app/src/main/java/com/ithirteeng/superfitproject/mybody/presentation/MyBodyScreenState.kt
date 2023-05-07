package com.ithirteeng.superfitproject.mybody.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class MyBodyScreenState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
)
