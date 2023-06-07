package com.ithirteeng.superfitproject.runnning.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class RunningState(
    val error: ErrorEntity? = null,
    val totalAmount: Int = 0,
    val currentAmount: Int = 0,
    val currentSteps: Int = 0,
    val baseSteps: Int = 0,
    val isLoading: Boolean = false,
    val isFinishedSuccessfully: Boolean = false,
    val isFinishedUnsuccessfully: Boolean = false,
)
