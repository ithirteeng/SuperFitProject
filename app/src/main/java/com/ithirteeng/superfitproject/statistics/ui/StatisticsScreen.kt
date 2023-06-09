package com.ithirteeng.superfitproject.statistics.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.ui.BackButton
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.statistics.presentation.StatisticsIntent
import com.ithirteeng.superfitproject.statistics.presentation.StatisticsViewModel
import com.ithirteeng.superfitproject.statistics.ui.graphs.TrainGraph
import com.ithirteeng.superfitproject.statistics.ui.graphs.WeightGraph
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
            } else if (state.ifBackButtonPressed) {
                LocalNavigator.currentOrThrow.pop()
            }

            Column(
                modifier = Modifier
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                BackButton {
                    viewModel.accept(StatisticsIntent.BackButtonClick)
                }
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 20.dp),
                    text = stringResource(id = R.string.weight),
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
                if (state.paramsData.isNotEmpty()) {
                    WeightGraph(paramsHistoryData = state.paramsData)
                }
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = stringResource(id = R.string.training),
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
                ExercisesButtons(selectedExercise = state.selectedType, onButtonClick = {
                    viewModel.accept(StatisticsIntent.TrainingTypeButtonClick(it))
                })
                if (
                    state.crunchList.isNotEmpty() ||
                    state.pushUpsList.isNotEmpty() ||
                    state.squatsList.isNotEmpty() ||
                    state.plankList.isNotEmpty() ||
                    state.runningList.isNotEmpty()
                ) {
                    TrainGraph(
                        exerciseType = state.selectedType,
                        pushUpsList = state.pushUpsList,
                        plankList = state.plankList,
                        crunchList = state.crunchList,
                        squatsList = state.squatsList,
                        runningList = state.runningList
                    )
                }
            }
        }
    }

    @Composable
    private fun ExercisesButtons(
        selectedExercise: ExerciseType,
        onButtonClick: (exerciseType: ExerciseType) -> Unit,
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 22.dp, start = 20.dp, end = 20.dp)
        ) {
            ExerciseButton(
                isSelected = selectedExercise == ExerciseType.PLANK,
                exerciseType = ExerciseType.PLANK
            ) {
                onButtonClick(ExerciseType.PLANK)
            }
            ExerciseButton(
                isSelected = selectedExercise == ExerciseType.PUSH_UP,
                exerciseType = ExerciseType.PUSH_UP
            ) {
                onButtonClick(ExerciseType.PUSH_UP)
            }
            ExerciseButton(
                isSelected = selectedExercise == ExerciseType.CRUNCH,
                exerciseType = ExerciseType.CRUNCH
            ) {
                onButtonClick(ExerciseType.CRUNCH)
            }
            ExerciseButton(
                isSelected = selectedExercise == ExerciseType.RUNNING,
                exerciseType = ExerciseType.RUNNING
            ) {
                onButtonClick(ExerciseType.RUNNING)
            }
            ExerciseButton(
                isSelected = selectedExercise == ExerciseType.SQUATS,
                exerciseType = ExerciseType.SQUATS
            ) {
                onButtonClick(ExerciseType.SQUATS)
            }
        }

    }

    @Composable
    private fun ExerciseButton(isSelected: Boolean, exerciseType: ExerciseType, click: () -> Unit) {
        val text = when (exerciseType) {
            ExerciseType.PUSH_UP -> R.string.push_ups
            ExerciseType.PLANK -> R.string.plank
            ExerciseType.CRUNCH -> R.string.crunch
            ExerciseType.SQUATS -> R.string.squats
            ExerciseType.RUNNING -> R.string.running
        }

        OutlinedButton(
            onClick = { click() },
            modifier = Modifier.padding(end = 20.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isSelected) Violet.copy(alpha = 0.08f) else GrayDark,
                contentColor = if (isSelected) Violet else Color.White.copy(alpha = 0.87f)
            ),
            border = BorderStroke(
                1.dp,
                if (isSelected) Violet else Color.White.copy(alpha = 0.12f)
            )
        ) {
            Text(
                text = stringResource(id = text),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = if (isSelected) Violet else Color.White.copy(alpha = 0.87f)
                )
            )
        }
    }
}