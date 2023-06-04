package com.ithirteeng.superfitproject.common.photos.domain.repository

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity

interface PhotosRepository {
    suspend fun uploadPhoto(image: ByteArray): ImageEntity

    suspend fun getPhotos(): List<ImageEntity>
}