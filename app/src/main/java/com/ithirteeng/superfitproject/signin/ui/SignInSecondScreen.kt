package com.ithirteeng.superfitproject.signin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.AuthHeaderText
import com.ithirteeng.superfitproject.common.ui.BackgroundImage
import com.ithirteeng.superfitproject.signin.di.SIGN_IN_SECOND_VIEW_MODEL
import com.ithirteeng.superfitproject.signin.presentation.second.SignInSecondScreenViewModel
import com.ithirteeng.superfitproject.signin.presentation.second.SignInSecondState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

class SignInSecondScreen(private val email: String) : Screen {

    @Composable
    override fun Content() {
        val viewModel: SignInSecondScreenViewModel = koinViewModel(named(SIGN_IN_SECOND_VIEW_MODEL))
        SignIn(viewModel = viewModel)
    }

    @Composable
    private fun SignIn(viewModel: SignInSecondScreenViewModel) {
        val state = viewModel.state.observeAsState(SignInSecondState()).value
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundImage()
            BackButton {

            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AuthHeaderText()

                    Text(
                        modifier = Modifier.padding(vertical = 44.dp),
                        text = email,
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center
                    )





                }
            }
        }
    }

    @Composable
    private fun BackButton(onButtonClick: () -> Unit) {
        IconButton(
            onClick = onButtonClick,
            modifier = Modifier
                .padding(start = 20.dp, top = 40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_icon),
                tint = Color.White,
                contentDescription = stringResource(id = R.string.arrow_content_description)
            )
        }
    }

}