package com.ithirteeng.superfitproject.common.photos.domain.usecase

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import com.ithirteeng.superfitproject.common.photos.domain.repository.PhotosRepository
import com.ithirteeng.superfitproject.common.utils.provideResult

class UploadPhotoUseCase(
    private val repository: PhotosRepository,
) {
    suspend operator fun invoke(image: ByteArray): Result<ImageEntity> {
        return provideResult {
            repository.uploadPhoto(image = image)
        }
    }
}