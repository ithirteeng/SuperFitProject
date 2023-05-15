package com.ithirteeng.superfitproject.crunch.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class CrunchScreenState(
    val isFinished: Boolean = false,
    val crunchesAmount: Int = 10,
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
)
