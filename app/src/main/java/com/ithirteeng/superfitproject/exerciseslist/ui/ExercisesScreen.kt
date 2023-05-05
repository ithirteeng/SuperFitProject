package com.ithirteeng.superfitproject.exerciseslist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.ExerciseCard
import com.ithirteeng.superfitproject.common.ui.ImageHeader
import com.ithirteeng.superfitproject.common.ui.theme.BackButton
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.exerciseslist.presentation.ExercisesScreenIntent
import com.ithirteeng.superfitproject.exerciseslist.presentation.ExercisesScreenViewModel
import org.koin.androidx.compose.koinViewModel

class ExercisesScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ExercisesScreenViewModel = koinViewModel()
        viewModel.accept(ExercisesScreenIntent.Initial)
        ExercisesScreenView(viewModel = viewModel)
    }

    @Composable
    private fun ExercisesScreenView(viewModel: ExercisesScreenViewModel) {
        val state = viewModel.state.collectAsState().value
        if (state.isLoading) {
            CircularProgressIndicator(
                color = GrayDark
            )
        } else if (state.error != null) {
            ErrorAlertDialog(errorEntity = state.error) {
                //todo: check if error can appear
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    ImageHeader()
                    BackButton {
                        viewModel.accept(ExercisesScreenIntent.BackButtonClick)
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ) {
                    LazyColumn(
                        Modifier.padding(bottom = 28.dp)
                    ) {
                        item {
                            ExercisesText()
                        }
                        items(state.exercisesList) {
                            ExerciseCard(imageId = it.imageId, exerciseEntity = it) { entity ->
                                viewModel.accept(ExercisesScreenIntent.ExerciseClick(entity))
                            }
                        }
                    }

                }
            }
        }
        if (state.completionModel.ifBackButtonClicked) {
            LocalNavigator.currentOrThrow.pop()
        } else if (state.completionModel.exerciseClicked != null) {
            // todo: navigate to exercise screen
        }
    }

    @Composable
    fun ExercisesText() {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.exercises),
            style = MaterialTheme.typography.h5
        )

    }
}