package com.ithirteeng.superfitproject.pushups.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class PushUpsState(
    val error: ErrorEntity? = null,
    val currentAmount: Int = 0,
    val totalAmount: Int = 0,
    val isLoading: Boolean = false,
    val isFinishedSuccessfully: Boolean = false,
    val isFinishedUnsuccessfully: Boolean = false,
)