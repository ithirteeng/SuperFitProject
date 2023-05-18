package com.ithirteeng.superfitproject.plank.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class PlankState(
    val error: ErrorEntity? = null,
    val currentTime: Int = 0,
    val totalTime: Int = 20,
    val isTimerRunning: Boolean = false,
    val isLoading: Boolean = false,
    val isFinished: Boolean = false,
)