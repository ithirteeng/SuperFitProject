package com.ithirteeng.superfitproject.mybody.di

import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myBodyModule = module {
    viewModel {
        MyBodyScreenViewModel()
    }
}