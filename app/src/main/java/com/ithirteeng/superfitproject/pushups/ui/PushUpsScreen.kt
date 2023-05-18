package com.ithirteeng.superfitproject.pushups.ui

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
import com.ithirteeng.superfitproject.pushups.presentation.PushUpsIntent
import com.ithirteeng.superfitproject.pushups.presentation.PushUpsScreenViewModel
import com.ithirteeng.superfitproject.pushups.utils.PushUpsExerciseHelper
import org.koin.androidx.compose.koinViewModel

class PushUpsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: PushUpsScreenViewModel = koinViewModel()
        viewModel.accept(PushUpsIntent.Initial)
        val sensorManager =
            LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val pushUpsExerciseHelper = PushUpsExerciseHelper(sensorManager = sensorManager) {
            viewModel.accept(PushUpsIntent.ActionIntent)
        }
        PushUpsScreenView(viewModel = viewModel, pushUpsExerciseHelper)
    }

    @Composable
    private fun PushUpsScreenView(
        viewModel: PushUpsScreenViewModel,
        pushUpsExerciseHelper: PushUpsExerciseHelper,
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
                    viewModel.accept(PushUpsIntent.DismissErrorDialog)
                }
            } else {

                if (state.isFinishedUnsuccessfully) {
                    LocalNavigator.currentOrThrow.pop()
                    pushUpsExerciseHelper.unregisterSensorListener()
                } else if (state.isFinishedSuccessfully) {
                    LocalNavigator.currentOrThrow.pop()
                    pushUpsExerciseHelper.unregisterSensorListener()
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
                            text = stringResource(id = R.string.push_ups),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                        ExerciseCircleView(
                            amount = state.currentAmount,
                            textBelow = stringResource(id = R.string.times_left)
                        )
                    }
                    FinishExerciseButton {
                        viewModel.accept(PushUpsIntent.FinishButtonClick)
                    }
                    BackHandler {
                        viewModel.accept(PushUpsIntent.FinishButtonClick)
                    }
                }
            }
        }
    }

}