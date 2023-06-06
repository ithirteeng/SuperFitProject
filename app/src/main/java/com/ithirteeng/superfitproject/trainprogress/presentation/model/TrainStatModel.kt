package com.ithirteeng.superfitproject.trainprogress.presentation.model

data class TrainStatModel(
    val amount: String? = null,
    val progress: String? = null,
    val indicator: ProgressIndicator = ProgressIndicator.NULL,
)
