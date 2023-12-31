package com.ithirteeng.superfitproject.common.photos.domain.repository

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel

interface PhotosRepository {
    suspend fun uploadPhoto(image: ByteArray): ImageEntity

    suspend fun getPhotos(): List<ImageEntity>

    suspend fun downloadPhoto(imageEntity: ImageEntity): ImageModel
}