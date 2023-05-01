package com.ithirteeng.superfitproject.signup.ui

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
import com.ithirteeng.superfitproject.common.ui.AuthHeaderText
import com.ithirteeng.superfitproject.common.ui.BackgroundImage
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.signin.ui.SignInFirstScreen
import com.ithirteeng.superfitproject.signup.presentation.SignUpEvent
import com.ithirteeng.superfitproject.signup.presentation.SignUpScreenViewModel
import com.ithirteeng.superfitproject.signup.presentation.SignUpState
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpTextField
import org.koin.androidx.compose.koinViewModel

class SignUpScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: SignUpScreenViewModel = koinViewModel()
        viewModel.accept(SignUpEvent.Initial)
        SignUp(viewModel = viewModel)
    }


    @Composable
    private fun SignUp(viewModel: SignUpScreenViewModel) {
        val state = viewModel.state.observeAsState(SignUpState()).value
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
                    viewModel.accept(SignUpEvent.DismissError)
                }
            } else if (state.completionModel.isRequestCompleted) {
                // todo navigate to main screen
            } else if (state.completionModel.isValidated) {
                //todo send event
            } else if (state.completionModel.isSignInButtonPressed) {
                LocalNavigator.currentOrThrow.replace(SignInFirstScreen())
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
                            value = state.data.userName,
                            placeHolder = stringResource(id = R.string.username),
                            onValueChanged = { value ->
                                viewModel.accept(
                                    SignUpEvent.TextFieldChange(SignUpTextField.USER_NAME, value)
                                )
                            }
                        )
                        MyTextField(
                            value = state.data.email,
                            placeHolder = stringResource(id = R.string.email),
                            onValueChanged = { value ->
                                viewModel.accept(
                                    SignUpEvent.TextFieldChange(SignUpTextField.EMAIL, value)
                                )
                            }
                        )
                        MyTextField(
                            value = state.data.code,
                            placeHolder = stringResource(id = R.string.code),
                            onValueChanged = { value ->
                                viewModel.accept(
                                    SignUpEvent.TextFieldChange(SignUpTextField.PASSWORD, value)
                                )
                            }
                        )
                        MyTextField(
                            value = state.data.repeatCode,
                            placeHolder = stringResource(id = R.string.repeat_code),
                            onValueChanged = { value ->
                                viewModel.accept(
                                    SignUpEvent.TextFieldChange(
                                        SignUpTextField.REPEAT_PASSWORD,
                                        value
                                    )
                                )
                            }
                        )
                        SignUpButton {
                            viewModel.accept(SignUpEvent.SignUpButtonClick)
                        }
                    }
                    SignInButton {
                        viewModel.accept(SignUpEvent.SignInButtonClick)
                    }

                }
            }
        }
    }

    @Composable
    private fun MyTextField(
        value: String,
        placeHolder: String,
        onValueChanged: (value: String) -> Unit,
    ) {
        com.ithirteeng.superfitproject.common.MyTextField(
            placeHolderString = placeHolder,
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
                painter = painterResource(id = R.drawable.arrow_back_icon),
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