package com.ithirteeng.superfitproject.image.di

import com.ithirteeng.superfitproject.image.presentation.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageModule = module {
    viewModel {
        ImageViewModel()
    }
}