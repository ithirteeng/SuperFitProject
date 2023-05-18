package com.ithirteeng.superfitproject.squats.ui

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
import com.ithirteeng.superfitproject.common.ui.ExerciseCircleView
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.squats.presentation.SquatsIntent
import com.ithirteeng.superfitproject.squats.presentation.SquatsScreenViewModel
import org.koin.androidx.compose.koinViewModel

class SquatsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SquatsScreenViewModel = koinViewModel()
        viewModel.accept(SquatsIntent.Initial)
        SquatsScreenView(viewModel = viewModel)
    }

    @Composable
    private fun SquatsScreenView(viewModel: SquatsScreenViewModel) {
        val state = viewModel.state.collectAsState().value

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(SquatsIntent.DismissErrorDialog)
                }
            } else {

                if (state.isFinishedUnsuccessfully) {
                    LocalNavigator.currentOrThrow.pop()
                } else if (state.isFinishedSuccessfully) {

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayDark),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            modifier = Modifier
                                .padding(top = 56.dp, bottom = 64.dp)
                                .statusBarsPadding(),
                            text = stringResource(id = R.string.squats),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h1,
                            color = Color.White
                        )
                        ExerciseCircleView(
                            amount = state.currentAmount,
                            textBelow = stringResource(id = R.string.times_left)
                        )
                    }
                }
                BackButton {
                    viewModel.accept(SquatsIntent.BackButtonClick)
                }
            }
        }
    }
}