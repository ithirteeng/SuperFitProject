package com.ithirteeng.superfitproject.trainprogress.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.trainprogress.presentation.model.TrainStatModel

data class TrainProgressState(
    val isLoading: Boolean = false,
    val ifBackButtonPressed: Boolean = false,
    val error: ErrorEntity? = null,

    val pushUpsTrain: TrainStatModel = TrainStatModel(),
    val plankTrain: TrainStatModel = TrainStatModel(),
    val crunchTrain: TrainStatModel = TrainStatModel(),
    val squatsTrain: TrainStatModel = TrainStatModel(),
    val runningTrain: TrainStatModel = TrainStatModel(),

    )
