package com.ithirteeng.superfitproject.success.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.ithirteeng.superfitproject.common.ui.FinishExerciseButton
import com.ithirteeng.superfitproject.common.ui.SuccessCircleView
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.success.presentation.SuccessIntent
import com.ithirteeng.superfitproject.success.presentation.SuccessScreenViewModel
import org.koin.androidx.compose.koinViewModel

class SuccessScreen(
    private val screenName: String,
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: SuccessScreenViewModel = koinViewModel()
        viewModel.accept(SuccessIntent.Initial)
        SuccessScreenView(viewModel = viewModel)
    }


    @Composable
    private fun SuccessScreenView(viewModel: SuccessScreenViewModel) {
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark)
        ) {
            if (state.isFinished) {
                LocalNavigator.currentOrThrow.pop()
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
                        text = screenName,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h1,
                        color = Color.White
                    )
                    SuccessCircleView()
                }
                FinishExerciseButton(text = stringResource(id = R.string.go_home)) {
                    viewModel.accept(SuccessIntent.GoHomeButtonClick)
                }
            }


        }
    }

}