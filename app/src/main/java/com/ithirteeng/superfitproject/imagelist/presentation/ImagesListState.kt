package com.ithirteeng.superfitproject.imagelist.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel

data class ImagesListState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val ifBackButtonClicked: Boolean = false,
    val imagesList: List<ImageModel>? = null,
    val imageClicked: ImageModel? = null,
)
