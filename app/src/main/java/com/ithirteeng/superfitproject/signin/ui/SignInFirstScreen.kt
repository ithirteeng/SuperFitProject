package com.ithirteeng.superfitproject.signin.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.MyTextField
import com.ithirteeng.superfitproject.common.ui.AuthHeaderText
import com.ithirteeng.superfitproject.common.ui.BackgroundImage
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.signin.di.SIGN_IN_FIRST_VIEW_MODEL
import com.ithirteeng.superfitproject.signin.presentation.first.SignInFirstEvent
import com.ithirteeng.superfitproject.signin.presentation.first.SignInFirstScreenViewModel
import com.ithirteeng.superfitproject.signin.presentation.first.SignInFirstState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

class SignInFirstScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: SignInFirstScreenViewModel = koinViewModel(named(SIGN_IN_FIRST_VIEW_MODEL))
        viewModel.accept(SignInFirstEvent.Initial)
        SignIn(viewModel = viewModel)
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun SignIn(viewModel: SignInFirstScreenViewModel) {
        val state = viewModel.state.observeAsState(SignInFirstState()).value
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundImage()

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(SignInFirstEvent.DismissError)
                }
            } else if (state.isCompleted) {
                LocalNavigator.currentOrThrow.push(SignInSecondScreen(state.userName))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AuthHeaderText()
                    Column(modifier = Modifier.padding(horizontal = 52.dp)) {
                        MyTextField(
                            onValueChanged = {
                                viewModel.accept(SignInFirstEvent.ChangeTextField(it))
                            },
                            value = state.userName
                        )
                        SignInButton {
                            viewModel.accept(SignInFirstEvent.SignInFirstButtonCLick(state.userName))
                        }
                    }
                    SignUpButton {
                        viewModel.accept(SignInFirstEvent.SignUpButtonClickFirst)
                    }
                }
            }
        }
    }

    @Composable
    private fun MyTextField(onValueChanged: (value: String) -> Unit, value: String) {
        MyTextField(
            placeHolderString = stringResource(id = R.string.username),
            value = value,
            onValueChanged = {
                onValueChanged(it)
            }
        )
    }

    @Composable
    private fun SignInButton(onButtonClick: () -> Unit) {
        TextButton(
            onClick = {
                onButtonClick()
            },
            modifier = Modifier.padding(top = 12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
            )

            Icon(
                modifier = Modifier
                    .padding(start = 8.dp),
                painter = painterResource(id = R.drawable.arrow_right_icon),
                contentDescription = stringResource(id = R.string.arrow_content_description)
            )

        }
    }

    @Composable
    private fun SignUpButton(onButtonClick: () -> Unit) {
        TextButton(
            onClick = {
                onButtonClick()
            },
            modifier = Modifier
                .padding(bottom = 34.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )

            Icon(
                modifier = Modifier
                    .padding(start = 8.dp),
                painter = painterResource(id = R.drawable.arrow_right_icon),
                contentDescription = stringResource(id = R.string.arrow_content_description)
            )

        }
    }


}