package com.ithirteeng.superfitproject.common.photos.domain.usecase

import com.ithirteeng.superfitproject.common.photos.domain.entity.ImageEntity
import com.ithirteeng.superfitproject.common.photos.domain.repository.PhotosRepository
import com.ithirteeng.superfitproject.common.utils.provideResult

class GetPhotosListUseCase(
    private val repository: PhotosRepository,
) {
    suspend operator fun invoke(): Result<List<ImageEntity>> {
        return provideResult {
            repository.getPhotos().sortedBy { it.uploadTime }
        }
    }
}