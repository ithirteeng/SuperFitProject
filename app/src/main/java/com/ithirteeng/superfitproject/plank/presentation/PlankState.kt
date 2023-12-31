package com.ithirteeng.superfitproject.plank.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class PlankState(
    val error: ErrorEntity? = null,
    val totalTime: Int = 0,
    val isTimerRunning: Boolean = false,
    val isLoading: Boolean = false,
    val isFinishedSuccessfully: Boolean = false,
    val isFinishedUnsuccessfully: Boolean = false,
    val isStartDialogOpened: Boolean = true,
)