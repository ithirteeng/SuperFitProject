package com.ithirteeng.superfitproject.imagelist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel
import com.ithirteeng.superfitproject.common.ui.BackButton
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.common.utils.byDateOrderComparatorString
import com.ithirteeng.superfitproject.image.ui.ImageScreen
import com.ithirteeng.superfitproject.imagelist.presentation.ImagesListIntent
import com.ithirteeng.superfitproject.imagelist.presentation.ImagesListViewModel
import org.koin.androidx.compose.koinViewModel

class ImagesListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ImagesListViewModel = koinViewModel()
        viewModel.accept(ImagesListIntent.Initial)
        ImagesListView(viewModel = viewModel)
    }

    @Composable
    private fun ImagesListView(viewModel: ImagesListViewModel) {
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize(),
                    color = Violet
                )
            } else if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(ImagesListIntent.DismissError)
                }
            } else if (state.ifBackButtonClicked) {
                LocalNavigator.currentOrThrow.pop()
            } else if (state.imageClicked != null) {
                LocalNavigator.currentOrThrow.push(ImageScreen(state.imageClicked))
            }
            Column {
                BackButton {
                    viewModel.accept(ImagesListIntent.BackButtonClick)
                }
                if (state.imagesList != null) {
                    ImagesList(list = state.imagesList) {
                        viewModel.accept(ImagesListIntent.ImageClick(it))
                    }
                }
            }
        }
    }

    @Composable
    private fun ImagesList(list: List<ImageModel>, onImageClick: (imageMode: ImageModel) -> Unit) {
        val distinctDates = list.flatMap { listOf(it.date) }.distinct().sortedWith(
            byDateOrderComparatorString
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .wrapContentSize(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                for (date in distinctDates) {
                    val imagesList = list.filter { it.date == date }
                    item(span = { GridItemSpan(3) }) {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.h5,
                            color = Color.White
                        )
                    }
                    items(
                        count = imagesList.size
                    ) { index ->
                        Image(
                            bitmap = imagesList[index].bitmap.asImageBitmap(),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .height(104.dp)
                                .clickable {
                                    onImageClick(imagesList[index])
                                },
                        )
                    }
                }
            }
        )
    }
}