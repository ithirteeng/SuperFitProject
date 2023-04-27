package com.ithirteeng.superfitproject.signin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.MyTextField
import com.ithirteeng.superfitproject.signin.presentation.SignInScreenViewModel
import org.koin.androidx.compose.koinViewModel

class SignInScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: SignInScreenViewModel = koinViewModel()
        SignIn(viewModel = viewModel)
    }

    @Composable
    private fun SignIn(viewModel: SignInScreenViewModel) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundImage()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 68.dp),
                    text = stringResource(id = R.string.super_fit),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primary
                )
                UserNameTextField(viewModel = viewModel)
                SignUpButton(viewModel = viewModel)
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
    fun UserNameTextField(viewModel: SignInScreenViewModel) {
        val name = remember { mutableStateOf("") }
        Column(modifier = Modifier.padding(horizontal = 52.dp)) {
            MyTextField(
                placeHolderString = stringResource(id = R.string.username),
                value = name.value,
                onValueChanged = { name.value = it }
            )
            SignInButton(viewModel = viewModel)
        }

    }

    @Composable
    private fun SignInButton(viewModel: SignInScreenViewModel) {
        TextButton(
            onClick = {
                // todo: navigate to password screen
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
    private fun SignUpButton(viewModel: SignInScreenViewModel) {
        TextButton(
            onClick = {
                // todo: navigate to signup screen
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