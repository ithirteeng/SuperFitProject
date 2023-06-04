package com.ithirteeng.superfitproject.mybody.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel
import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType
import com.ithirteeng.superfitproject.mybody.presentation.model.ExitModel

data class MyBodyScreenState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val weight: String = "",
    val height: String = "",
    val exitModel: ExitModel = ExitModel(),
    val isRequestCompleted: Boolean = false,
    val isAlertDialogOpened: Boolean = false,
    val alertDialogType: AlertDialogType = AlertDialogType.WEIGHT,
    val alertTextFieldValue: String = "",
    val isPhotoPickerDialogOpened: Boolean = false,
    val firstImage: ImageModel? = null,
    val secondImage: ImageModel? = null,
)
