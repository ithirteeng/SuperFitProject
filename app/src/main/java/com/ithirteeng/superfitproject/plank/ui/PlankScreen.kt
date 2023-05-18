package com.ithirteeng.superfitproject.plank.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.BackButton
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.FinishExerciseButton
import com.ithirteeng.superfitproject.common.ui.TextAlertDialog
import com.ithirteeng.superfitproject.common.ui.Timer
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.plank.presentation.PlankIntent
import com.ithirteeng.superfitproject.plank.presentation.PlankScreenViewModel
import com.ithirteeng.superfitproject.success.ui.SuccessScreen
import org.koin.androidx.compose.koinViewModel

class PlankScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: PlankScreenViewModel = koinViewModel()
        viewModel.accept(PlankIntent.Initial)
        PlankScreenView(viewModel = viewModel)
    }

    @Composable
    private fun PlankScreenView(viewModel: PlankScreenViewModel) {
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(PlankIntent.DismissErrorDialog)
                }
            } else if (state.isStartDialogOpened) {
                TextAlertDialog(
                    onLaterButtonClick = {
                        viewModel.accept(PlankIntent.LaterButtonClick)
                    },
                    onGoButtonClick = {
                        viewModel.accept(PlankIntent.GoButtonClick)
                    },
                    header = stringResource(id = R.string.start_the_training),
                    text = stringResource(id = R.string.you_need_to_repeat).replace(
                        "%d",
                        state.totalTime.toString()
                    )
                )
            } else {
                if (state.isFinishedUnsuccessfully) {
                    LocalNavigator.currentOrThrow.pop()
                } else if (state.isFinishedSuccessfully) {
                    LocalNavigator.currentOrThrow.replace(
                        SuccessScreen(stringResource(id = R.string.plank))
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            modifier = Modifier
                                .padding(top = 56.dp, bottom = 64.dp)
                                .statusBarsPadding(),
                            text = stringResource(id = R.string.plank),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )

                        Timer(
                            text = stringResource(id = R.string.seconds_left),
                            totalTime = state.totalTime,
                            isTimerRunning = state.isTimerRunning
                        ) {
                            viewModel.accept(PlankIntent.FinishExercise)
                        }

                    }
                    FinishExerciseButton {
                        viewModel.accept(PlankIntent.FinishButtonClick)
                    }
                }
                BackButton {
                    viewModel.accept(PlankIntent.BackButtonClick)
                }
            }
        }
    }
}