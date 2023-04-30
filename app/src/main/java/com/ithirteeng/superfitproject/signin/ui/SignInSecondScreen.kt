package com.ithirteeng.superfitproject.signin.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.AuthHeaderText
import com.ithirteeng.superfitproject.common.ui.BackgroundImage
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.signin.di.SIGN_IN_SECOND_VIEW_MODEL
import com.ithirteeng.superfitproject.signin.presentation.second.SignInSecondEvent
import com.ithirteeng.superfitproject.signin.presentation.second.SignInSecondScreenViewModel
import com.ithirteeng.superfitproject.signin.presentation.second.SignInSecondState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@ExperimentalFoundationApi
class SignInSecondScreen(private val email: String) : Screen {

    @Composable
    override fun Content() {
        val viewModel: SignInSecondScreenViewModel = koinViewModel(named(SIGN_IN_SECOND_VIEW_MODEL))
        LifecycleEffect(
            onDisposed = {
                viewModel.accept(SignInSecondEvent.BackButtonClick)
            }
        )
        viewModel.accept(SignInSecondEvent.Initial(email))
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
                viewModel.accept(SignInSecondEvent.BackButtonClick)
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            } else if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(SignInSecondEvent.DismissError)
                }
            } else if (state.completionModel.isCompleted) {
                if (state.completionModel.isBackButtonPressed) {
                    if (LocalNavigator.currentOrThrow.canPop) {
                        LocalNavigator.currentOrThrow.pop()
                    } else {
                        LocalNavigator.currentOrThrow.replace(SignInFirstScreen())
                    }
                } else {
                   //LocalNavigator.currentOrThrow.replaceAll(TestScreen())
                    //todo navigate to main screens
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AuthHeaderText()
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 24.dp),
                            text = email,
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center
                        )
                        PasswordGrid(list = state.numbers) {
                            viewModel.accept(
                                SignInSecondEvent.NumberButtonClick(
                                    list = state.numbers,
                                    number = it
                                )
                            )
                        }
                    }
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

    @Composable
    private fun PasswordGrid(list: List<Int>, onItemClick: (number: String) -> Unit) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(bottom = 50.dp, start = 40.dp, end = 40.dp, top = 44.dp)
                .wrapContentSize(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            content = {
                items(
                    count = list.size,
                    key = { list[it] }
                ) { index ->
                    NumberButton(number = list[index].toString()) {
                        onItemClick(list[index].toString())
                    }
                }
            }
        )
    }


    @Composable
    private fun LazyGridItemScope.NumberButton(
        number: String,
        onButtonClick: () -> Unit,
    ) {
        TextButton(
            modifier = Modifier
                .background(Color.Transparent)
                .size(78.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .animateItemPlacement(
                    animationSpec = tween(
                        durationMillis = 600
                    )
                ),
            contentPadding = PaddingValues(0.dp),
            onClick = onButtonClick
        ) {
            Text(
                text = number,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2
            )
        }
    }

}