package com.ithirteeng.superfitproject.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.entity.ExerciseEntity
import com.ithirteeng.superfitproject.common.model.ExerciseType
import com.ithirteeng.superfitproject.common.ui.BaseCard
import com.ithirteeng.superfitproject.common.ui.ExerciseCard
import com.ithirteeng.superfitproject.common.ui.ImageHeader
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
                LazyColumn {
                    item {
                        MyBody(state = state)
                    }
                    item { 
                        LastExercises(state = state)
                    }

                    item {
                        ExerciseCard(imageId = R.drawable.exercise_image, exerciseEntity = ExerciseEntity(
                            name = ExerciseType.SQUATS.type,
                            descriptionId = R.string.squats_description,
                            type = ExerciseType.SQUATS
                        ))
                    }
                    item {
                        ExerciseCard(imageId = R.drawable.exercise_image, exerciseEntity = ExerciseEntity(
                            name = ExerciseType.SQUATS.type,
                            descriptionId = R.string.squats_description,
                            type = ExerciseType.SQUATS
                        ))
                    }
                    item {
                        ExerciseCard(imageId = R.drawable.exercise_image, exerciseEntity = ExerciseEntity(
                            name = ExerciseType.SQUATS.type,
                            descriptionId = R.string.squats_description,
                            type = ExerciseType.SQUATS
                        ))
                    }
                    item {
                        ExerciseCard(imageId = R.drawable.exercise_image, exerciseEntity = ExerciseEntity(
                            name = ExerciseType.SQUATS.type,
                            descriptionId = R.string.squats_description,
                            type = ExerciseType.SQUATS
                        ))
                    }
                    item {
                        ExerciseCard(imageId = R.drawable.exercise_image, exerciseEntity = ExerciseEntity(
                            name = ExerciseType.SQUATS.type,
                            descriptionId = R.string.squats_description,
                            type = ExerciseType.SQUATS
                        ))
                    }
                }
            }
        }
    }

    @Composable
    private fun MyBody(state: MainScreenState) {
        Column {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.my_body),
                style = MaterialTheme.typography.h5
            )
            BaseCard(imageId = R.drawable.exercise_image) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "hui")
                    Text(text = "hui1")
                    Text(text = "huisdfsgfjsdkfghlsdkfjhgskjfghdksjfhgskjfhgskjfhglksfhglsfglksdfhglsdkjfghdsklfghsdlkfjghsdlkfjghksdljfghsdkjfghsdkjfghdsklfjghdslkfjghsldkfjghlsdkfjghsdklfjghsdkfghldskfghsdljkfghdslkfghsdfgs2")
                }
            }
        }
    }

    @Composable
    private fun LastExercises(state: MainScreenState) {
        Column {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.last_exercises),
                style = MaterialTheme.typography.h5
            )
        }
    }
}