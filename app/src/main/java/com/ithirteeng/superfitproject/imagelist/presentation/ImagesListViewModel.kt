package com.ithirteeng.superfitproject.imagelist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel
import com.ithirteeng.superfitproject.common.photos.domain.usecase.DownloadPhotoUseCase
import com.ithirteeng.superfitproject.common.photos.domain.usecase.GetPhotosListUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImagesListViewModel(
    private val application: Application,
    private val getPhotosListUseCase: GetPhotosListUseCase,
    private val downloadPhotoUseCase: DownloadPhotoUseCase,
) : AndroidViewModel(application = application) {

    fun accept(intent: ImagesListIntent) {
        when (intent) {
            is ImagesListIntent.BackButtonClick -> onBackButtonClick()
            is ImagesListIntent.DismissError -> dismissError()
            is ImagesListIntent.ImageClick -> onImageClick(intent.image)
            is ImagesListIntent.Initial -> initState()
        }
    }

    private val _state = MutableStateFlow(ImagesListState())

    val state: StateFlow<ImagesListState> = _state

    private fun initState() {
        _state.value = ImagesListState(
            ifBackButtonClicked = false,
            error = null,
            imageClicked = null
        )
        getImagesList()
    }

    private fun getImagesList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getPhotosListUseCase()
                .onSuccess { rawList ->
                    val resultList = arrayListOf<ImageModel>()
                    for (entity in rawList) {
                        downloadPhotoUseCase(entity)
                            .onSuccess {
                                resultList.add(it)
                            }
                            .onFailure {
                                _state.value = _state.value.copy(
                                    isLoading = false,
                                    error = ErrorHelper.setupErrorEntity(it)
                                )
                            }
                    }
                    _state.value = _state.value.copy(
                        isLoading = false,
                        imagesList = resultList
                    )

                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorHelper.setupErrorEntity(it)
                    )
                }
        }
    }

    private fun onBackButtonClick() {
        _state.value = _state.value.copy(
            isLoading = false,
            ifBackButtonClicked = true
        )
    }

    private fun onImageClick(image: ImageModel) {
        _state.value = _state.value.copy(
            imageClicked = image
        )
    }

    private fun dismissError() {
        _state.value = _state.value.copy(
            isLoading = false,
            error = null
        )
    }

}