package com.ithirteeng.superfitproject.crunch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.ithirteeng.superfitproject.common.ui.ExerciseView
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.crunch.presentation.CrunchScreenIntent
import com.ithirteeng.superfitproject.crunch.presentation.CrunchScreenViewModel
import org.koin.androidx.compose.koinViewModel

class CrunchScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: CrunchScreenViewModel = koinViewModel()
        viewModel.accept(CrunchScreenIntent.Initial)
        CrunchScreenView(viewModel = viewModel)
    }

    @Composable
    private fun CrunchScreenView(viewModel: CrunchScreenViewModel) {
        val state = viewModel.state.collectAsState().value
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
                    text = stringResource(id = R.string.crunch),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1,
                    color = Color.White
                )
                ExerciseView(
                    amount = state.crunchesAmount,
                    textBelow = stringResource(id = R.string.need_to_do)
                )
            }
            FinishButton {
                viewModel.accept(CrunchScreenIntent.FinishButtonClick)
            }
        }

        if (state.isFinished) {
            LocalNavigator.currentOrThrow.pop()
        }
    }

    @Composable
    private fun FinishButton(onButtonClick: () -> Unit) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Violet),
            onClick = {
                onButtonClick()
            },
            contentPadding = PaddingValues(vertical = 13.dp)
        ) {
            Text(
                text = stringResource(id = R.string.finish),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        }
    }
}