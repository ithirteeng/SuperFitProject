package com.ithirteeng.superfitproject.runnning.ui

import android.content.Context
import android.hardware.SensorManager
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.ExerciseCircleView
import com.ithirteeng.superfitproject.common.ui.FinishExerciseButton
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.result.ui.ResultScreen
import com.ithirteeng.superfitproject.runnning.presentation.RunningIntent
import com.ithirteeng.superfitproject.runnning.presentation.RunningScreenViewModel
import com.ithirteeng.superfitproject.runnning.utils.RunningExerciseHelper
import org.koin.androidx.compose.koinViewModel

class RunningScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: RunningScreenViewModel = koinViewModel()
        viewModel.accept(RunningIntent.Initial)

        val sensorManager =
            LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val runningExerciseHelper = RunningExerciseHelper(
            sensorManager = sensorManager,
            onAction = {
                viewModel.accept(RunningIntent.ActionIntent(it))
            },
            onFirstAction = {
                viewModel.accept(RunningIntent.SetDefaultSteps(it))
            }
        )

        RunningScreenView(viewModel, runningExerciseHelper)
    }

    @Composable
    private fun RunningScreenView(
        viewModel: RunningScreenViewModel,
        runningExerciseHelper: RunningExerciseHelper,
    ) {
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
                    viewModel.accept(RunningIntent.DismissErrorDialog)
                }
            }
        }

        if (state.isFinishedUnsuccessfully) {
            val string = stringResource(id = R.string.meters_passed)
            LocalNavigator.currentOrThrow.replace(
                ResultScreen(
                    screenName = stringResource(id = R.string.running),
                    unSuccessScreenString = "${state.totalAmount - state.currentAmount} $string"
                )
            )
            runningExerciseHelper.unregisterSensorListener()
        } else if (state.isFinishedSuccessfully) {
            LocalNavigator.currentOrThrow.replace(
                ResultScreen(
                    screenName = stringResource(id = R.string.running),
                )
            )
            runningExerciseHelper.unregisterSensorListener()
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
                    text = stringResource(id = R.string.running),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1,
                    color = Color.White
                )
                ExerciseCircleView(
                    amount = state.currentAmount,
                    textBelow = stringResource(id = R.string.meters_passed)
                )
            }
            FinishExerciseButton {
                viewModel.accept(RunningIntent.FinishButtonClick)
            }
            BackHandler {
                viewModel.accept(RunningIntent.FinishButtonClick)
            }
        }

    }
}