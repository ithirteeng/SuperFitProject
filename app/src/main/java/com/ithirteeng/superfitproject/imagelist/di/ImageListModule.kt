package com.ithirteeng.superfitproject.imagelist.di

import com.ithirteeng.superfitproject.imagelist.presentation.ImagesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageListModule = module {
    viewModel {
        ImagesListViewModel(
            application = get(),
            getPhotosListUseCase = get(),
            downloadPhotoUseCase = get()
        )
    }
}