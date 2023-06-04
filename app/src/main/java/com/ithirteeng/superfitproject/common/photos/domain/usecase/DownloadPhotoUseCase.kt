package com.ithirteeng.superfitproject.common.photos.domain.usecase

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel
import com.ithirteeng.superfitproject.common.photos.domain.repository.PhotosRepository
import com.ithirteeng.superfitproject.common.utils.provideResult

class DownloadPhotoUseCase(
    private val repository: PhotosRepository,
) {
    suspend operator fun invoke(imageEntity: ImageEntity): Result<ImageModel> {
        return provideResult {
            repository.downloadPhoto(imageEntity)
        }
    }
}