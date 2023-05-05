@file:OptIn(ExperimentalFoundationApi::class)

package com.ithirteeng.superfitproject.splash.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.main.ui.MainScreen
import com.ithirteeng.superfitproject.signin.ui.SignInFirstScreen
import com.ithirteeng.superfitproject.signin.ui.SignInSecondScreen
import com.ithirteeng.superfitproject.signup.ui.SignUpScreen
import com.ithirteeng.superfitproject.splash.presentation.SplashScreenIntent
import com.ithirteeng.superfitproject.splash.presentation.SplashScreenViewModel
import com.ithirteeng.superfitproject.splash.presentation.model.SplashNextScreenType
import org.koin.androidx.compose.koinViewModel

class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SplashScreenViewModel = koinViewModel()
        viewModel.accept(SplashScreenIntent.CheckDataScreenIntent)
        Screen(viewModel = viewModel)

    }

    @Composable
    private fun Screen(viewModel: SplashScreenViewModel) {
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundImage()
            HeaderText(message = stringResource(id = R.string.super_fit))
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else if (state.error != null) {
                //todo alert dialog
            } else if (state.completionModel.isCompleted) {
                when (state.completionModel.nextScreenType) {
                    SplashNextScreenType.REGISTRATION -> {
                        LocalNavigator.currentOrThrow.replace(
                            SignUpScreen()
                        )
                    }

                    SplashNextScreenType.LOGIN -> LocalNavigator.currentOrThrow.replace(
                        SignInFirstScreen()
                    )

                    SplashNextScreenType.LOGIN2 -> LocalNavigator.currentOrThrow.replace(
                        SignInSecondScreen(state.userEmail.toString())
                    )

                    SplashNextScreenType.MAIN -> {
                        LocalNavigator.currentOrThrow.replace(MainScreen())
                    }
                }
            }
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

}