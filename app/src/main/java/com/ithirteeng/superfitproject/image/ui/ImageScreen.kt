package com.ithirteeng.superfitproject.image.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel
import com.ithirteeng.superfitproject.common.ui.BackButton
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.image.presentation.ImageIntent
import com.ithirteeng.superfitproject.image.presentation.ImageViewModel
import org.koin.androidx.compose.koinViewModel

class ImageScreen(private val imageModel: ImageModel) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ImageViewModel = koinViewModel()
        viewModel.accept(ImageIntent.Initial)
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark)
        ) {
            var scale by remember { mutableStateOf(1f) }
            val transformableState = rememberTransformableState { zoomChange, _, _ ->
                scale *= zoomChange
                if (scale < 1) {
                    scale = 1F
                } else if (scale > 5) {
                    scale = 5F
                }
            }
            if (state.ifBackButtonClicked) {
                LocalNavigator.currentOrThrow.pop()
            }
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .transformable(transformableState)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .transformable(state = transformableState)
                    .pointerInput(Unit) {

                    },
                bitmap = imageModel.bitmap.asImageBitmap(),
                contentDescription = null
            )
            BackButton {
                viewModel.accept(ImageIntent.BackButtonClick)
            }

        }
    }
}