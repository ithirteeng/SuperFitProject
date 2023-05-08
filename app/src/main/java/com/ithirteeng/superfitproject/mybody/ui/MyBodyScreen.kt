package com.ithirteeng.superfitproject.mybody.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.GrayLight
import com.ithirteeng.superfitproject.common.ui.theme.GrayWhite
import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenIntent
import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenState
import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenViewModel
import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType
import org.koin.androidx.compose.koinViewModel

class MyBodyScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: MyBodyScreenViewModel = koinViewModel()
        viewModel.accept(MyBodyScreenIntent.Initial)
        MyBodyScreenView(viewModel = viewModel)
    }

    @Composable
    fun MyBodyScreenView(viewModel: MyBodyScreenViewModel) {
        val state = viewModel.state.collectAsState().value
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.White
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GrayDark),
                contentAlignment = Alignment.TopCenter
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .statusBarsPadding()
                ) {
                    item { HeaderText(text = stringResource(id = R.string.my_body)) }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            horizontalArrangement = Arrangement.spacedBy(48.dp)
                        ) {
                            ParamView(text = state.weight) {
                                viewModel.accept(
                                    MyBodyScreenIntent.OpenAlertDialog(
                                        AlertDialogType.WEIGHT
                                    )
                                )
                            }
                            ParamView(text = state.height) {
                                viewModel.accept(
                                    MyBodyScreenIntent.OpenAlertDialog(
                                        AlertDialogType.HEIGHT
                                    )
                                )
                            }
                        }
                    }
                    item { HeaderText(text = stringResource(id = R.string.my_progress)) }

                }
            }
        }

        if (state.error != null) {
            //todo show error
        } else if (state.isAlertDialogOpened) {
            ChangeParamsAlertDialog(
                state = state,
                onDismiss = {
                    viewModel.accept(MyBodyScreenIntent.CloseAlertDialog)
                },
                onConfirmButtonClick = {}
            )

        }

    }

    @Composable
    private fun HeaderText(text: String) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = text,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Start,
            color = Color.White
        )
    }

    @Composable
    private fun RowScope.ParamView(text: String, onEditButtonClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h4,
                color = Color.White,
            )
            Text(
                text = stringResource(id = R.string.edit),
                style = MaterialTheme.typography.body1,
                color = GrayWhite,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {
                        onEditButtonClick()
                    }
            )
        }
    }

    @Composable
    private fun ChangeParamsAlertDialog(
        state: MyBodyScreenState,
        onDismiss: () -> Unit,
        onConfirmButtonClick: () -> Unit,
    ) {
        AlertDialog(
            modifier = Modifier
                .background(GrayLight),
            onDismissRequest = {
                onDismiss()
            },
            buttons = {},
            text = {},
            title = {
                Text(
                    text = stringResource(id = R.string.change_your) + " " + state.alertDialogType.type,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp)
                )
            }
        )
    }
}