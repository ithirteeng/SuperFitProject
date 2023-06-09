package com.ithirteeng.superfitproject.statistics.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.statistics.presentation.StatisticsIntent
import com.ithirteeng.superfitproject.statistics.presentation.StatisticsViewModel
import org.koin.androidx.compose.koinViewModel

class StatisticsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: StatisticsViewModel = koinViewModel()
        viewModel.accept(StatisticsIntent.Initial)
        StatisticsScreenView(viewModel = viewModel)
    }

    @Composable
    private fun StatisticsScreenView(viewModel: StatisticsViewModel) {
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.statistics_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(StatisticsIntent.DismissError)
                }
            }
        }
    }
}