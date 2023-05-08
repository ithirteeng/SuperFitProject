package com.ithirteeng.superfitproject.mybody.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.mybody.presentation.model.ExitModel

data class MyBodyScreenState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val weight: String = "",
    val height: String = "",
    val exitModel: ExitModel = ExitModel(),
    val isRequestCompleted: Boolean = false,
)
