package com.ithirteeng.superfitproject.common.photos.di

import com.ithirteeng.superfitproject.common.network.utils.TOKEN_NETWORK_TOOLS
import com.ithirteeng.superfitproject.common.network.utils.createRetrofitService
import com.ithirteeng.superfitproject.common.photos.data.api.PhotosApi
import com.ithirteeng.superfitproject.common.photos.data.repository.PhotosRepositoryImpl
import com.ithirteeng.superfitproject.common.photos.domain.repository.PhotosRepository
import com.ithirteeng.superfitproject.common.photos.domain.usecase.DownloadPhotoUseCase
import com.ithirteeng.superfitproject.common.photos.domain.usecase.GetPhotosListUseCase
import com.ithirteeng.superfitproject.common.photos.domain.usecase.UploadPhotoUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val photosModule = module {
    single { createRetrofitService<PhotosApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<PhotosRepository> { PhotosRepositoryImpl(api = get()) }

    factory { UploadPhotoUseCase(repository = get()) }
    factory { GetPhotosListUseCase(repository = get()) }
    factory { DownloadPhotoUseCase(repository = get()) }
}