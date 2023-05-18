package com.ithirteeng.superfitproject.squats.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class SquatsState(
    val error: ErrorEntity? = null,
    val currentAmount: Int = 0,
    val isLoading: Boolean = false,
    val isFinishedSuccessfully: Boolean = false,
    val isFinishedUnsuccessfully: Boolean = false,
)
