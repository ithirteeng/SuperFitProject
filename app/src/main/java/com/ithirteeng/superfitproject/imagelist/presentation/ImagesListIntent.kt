package com.ithirteeng.superfitproject.imagelist.presentation

import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel

sealed class ImagesListIntent {
    class ImageClick(val image: ImageModel): ImagesListIntent()
    object DismissError: ImagesListIntent()
    object BackButtonClick: ImagesListIntent()
    object Initial: ImagesListIntent()
}