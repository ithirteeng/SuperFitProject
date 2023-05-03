package com.ithirteeng.superfitproject.main.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class MainScreenState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
)