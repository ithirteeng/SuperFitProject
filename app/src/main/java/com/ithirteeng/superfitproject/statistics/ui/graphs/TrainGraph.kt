package com.ithirteeng.superfitproject.statistics.ui.graphs

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseType
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import com.ithirteeng.superfitproject.common.ui.theme.Violet

@Composable
fun TrainGraph(
    exerciseType: ExerciseType,
    pushUpsList: List<TrainingEntity>?,
    plankList: List<TrainingEntity>?,
    crunchList: List<TrainingEntity>?,
    squatsList: List<TrainingEntity>?,
    runningList: List<TrainingEntity>?,
) {
    var yStep = 0
    var points = listOf<Float>()
    var yValues = listOf<Int>()
    var xValues = listOf<String>()

    when (exerciseType) {
        ExerciseType.PUSH_UP -> {
            yStep = 5
            points = pushUpsList?.flatMap { listOf(it.repeatCount.toFloat()) } ?: listOf()
            yValues = listOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)
            xValues = pushUpsList?.flatMap { listOf(it.date) } ?: listOf()
        }

        ExerciseType.PLANK -> {
            yStep = 5
            points = plankList?.flatMap { listOf(it.repeatCount.toFloat()) } ?: listOf()
            yValues = listOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)
            xValues = plankList?.flatMap { listOf(it.date) } ?: listOf()
        }

        ExerciseType.CRUNCH -> {
            yStep = 5
            points = crunchList?.flatMap { listOf(it.repeatCount.toFloat()) } ?: listOf()
            yValues = listOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)
            xValues = crunchList?.flatMap { listOf(it.date) } ?: listOf()
        }

        ExerciseType.SQUATS -> {
            yStep = 5
            points = squatsList?.flatMap { listOf(it.repeatCount.toFloat()) } ?: listOf()
            yValues = listOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)
            xValues = squatsList?.flatMap { listOf(it.date) } ?: listOf()
        }

        ExerciseType.RUNNING -> {
            yStep = 10
            points = runningList?.flatMap { listOf(it.repeatCount.toFloat()) } ?: listOf()
//            yValues = listOf(50, 100, 150, 200, 250, 300, 350, 400, 450, 500)
//            yValues = listOf(0, 20, 40, 60, 80, 100, 120, 140, 160, 180)
            yValues = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
            xValues = runningList?.flatMap { listOf(it.date) } ?: listOf()
        }
    }

    TrainGraphView(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(top = 20.dp, end = 25.dp, start = 20.dp)
            .height(240.dp)
            .width(points.size * 80.dp),
        xValues = xValues,
        yValues = yValues,
        points = points,
        paddingSpace = 40.dp,
        verticalStep = yStep,
        gridColor = Color.White,
        lineColor = Violet,
    )
}