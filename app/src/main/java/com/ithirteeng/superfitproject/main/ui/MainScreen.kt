@file:OptIn(ExperimentalFoundationApi::class)

package com.ithirteeng.superfitproject.main.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.BaseCard
import com.ithirteeng.superfitproject.common.ui.ExerciseCard
import com.ithirteeng.superfitproject.common.ui.ImageHeader
import com.ithirteeng.superfitproject.common.ui.theme.GrayWhite
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.main.presentation.MainScreenIntent
import com.ithirteeng.superfitproject.main.presentation.MainScreenState
import com.ithirteeng.superfitproject.main.presentation.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: MainScreenViewModel = koinViewModel()
        viewModel.accept(MainScreenIntent.Initial)
        MainScreenView(viewModel = viewModel)
    }

    @Composable
    private fun MainScreenView(viewModel: MainScreenViewModel) {
        val state = viewModel.state.collectAsState().value
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ImageHeader()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White,
            ) {
                CompositionLocalProvider(
                    LocalOverscrollConfiguration provides null
                ) {
                    LazyColumn {
                        item {
                            MyBody(state = state) {

                            }
                        }
                        item {
                            LastExercisesText()
                        }

                        items(state.data.exercises) {
                            ExerciseCard(imageId = it.imageId, exerciseEntity = it)
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun MyBody(state: MainScreenState, onDetailsButtonClick: () -> Unit) {
        Column {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.my_body),
                style = MaterialTheme.typography.h5
            )
            BaseCard(imageId = R.drawable.exercise_image) { modifier ->
                Column(
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        BodyDetail(
                            text = state.data.weight,
                            painter = painterResource(id = R.drawable.weight_icon)
                        )
                        BodyDetail(
                            text = state.data.height,
                            painter = painterResource(id = R.drawable.height_icon)
                        )
                    }
                    DetailsButton {
                        onDetailsButtonClick()
                    }
                }
            }
        }
    }

    @Composable
    private fun BodyDetail(text: String, painter: Painter) {
        Row(modifier = Modifier.padding(top = 12.dp, start = 10.dp)) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = Violet
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = text,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }

    @Composable
    private fun DetailsButton(onDetailsButtonClick: () -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 12.dp)
                .clickable {
                    onDetailsButtonClick()
                }

        ) {
            Text(
                text = stringResource(id = R.string.details),
                style = MaterialTheme.typography.body1,
                color = GrayWhite,
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = Modifier.padding(start = 4.dp),
                painter = painterResource(id = R.drawable.details_arrow_icon),
                contentDescription = null,
                tint = GrayWhite
            )


        }

    }

    @Composable
    private fun LastExercisesText() {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.last_exercises),
            style = MaterialTheme.typography.h5
        )
    }
}