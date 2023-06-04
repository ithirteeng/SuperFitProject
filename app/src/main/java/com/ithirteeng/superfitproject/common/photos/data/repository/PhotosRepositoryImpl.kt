package com.ithirteeng.superfitproject.common.photos.data.repository

import com.ithirteeng.superfitproject.common.photos.data.api.PhotosApi
import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import com.ithirteeng.superfitproject.common.photos.domain.repository.PhotosRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class PhotosRepositoryImpl(
    private val api: PhotosApi,
) : PhotosRepository {
    override suspend fun uploadPhoto(image: ByteArray): ImageEntity {
        val body = image.toRequestBody("image/jpg".toMediaType(), 0, image.size)
        val part = MultipartBody.Part.createFormData("file", "image.png", body)
        return api.uploadPhoto(part)
    }

    override suspend fun getPhotos(): List<ImageEntity> {
        return api.getPhotos()
    }
}