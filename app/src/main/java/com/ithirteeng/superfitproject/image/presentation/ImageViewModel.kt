package com.ithirteeng.superfitproject.image.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ImageViewModel : ViewModel() {
    fun accept(intent: ImageIntent) {
        when (intent) {
            ImageIntent.BackButtonClick -> onBackButtonClick()
            ImageIntent.Initial -> _state.value = ImageState(ifBackButtonClicked = false)
        }
    }

    private val _state = MutableStateFlow(ImageState())
    val state: StateFlow<ImageState> = _state

    private fun onBackButtonClick() {
        _state.value = _state.value.copy(
            ifBackButtonClicked = true
        )
    }
}