@file:OptIn(ExperimentalFoundationApi::class)

package com.ithirteeng.superfitproject.splash.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.signin.ui.SignInFirstScreen
import com.ithirteeng.superfitproject.splash.presentation.SplashEvent
import com.ithirteeng.superfitproject.splash.presentation.SplashScreenViewModel
import com.ithirteeng.superfitproject.splash.presentation.SplashState
import org.koin.androidx.compose.koinViewModel

class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SplashScreenViewModel = koinViewModel()
        Screen()
        viewModel.accept(SplashEvent.CheckDataEvent)
        ObserveData(viewModel = viewModel)
    }

    @Composable
    private fun Screen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundImage()
            HeaderText(message = stringResource(id = R.string.super_fit))
        }
    }

    @Composable
    private fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.pretty_background),
            contentDescription = stringResource(id = R.string.background_content_description),
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    private fun BoxScope.HeaderText(message: String) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 140.dp),
            text = message,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary
        )
    }

    @Composable
    private fun ObserveData(viewModel: SplashScreenViewModel) {
        when (viewModel.state.observeAsState().value) {
            is SplashState.CompleteCheck -> LocalNavigator.currentOrThrow.replace(
                SignInFirstScreen()
            )

            is SplashState.Error -> {}
            SplashState.Loading -> {}
            null -> {}
        }
    }

}