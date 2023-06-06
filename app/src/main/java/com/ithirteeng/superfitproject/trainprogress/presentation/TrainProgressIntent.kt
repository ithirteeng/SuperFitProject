package com.ithirteeng.superfitproject.trainprogress.presentation

sealed class TrainProgressIntent {
    object Initial: TrainProgressIntent()
    object BackButtonClick: TrainProgressIntent()
    object DismissError: TrainProgressIntent()
}