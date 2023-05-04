package com.ithirteeng.superfitproject.main.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.main.presentation.model.MainScreenData

data class MainScreenState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val data: MainScreenData = MainScreenData()
)