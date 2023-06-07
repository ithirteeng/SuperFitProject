package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.theme.Montserrat
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import kotlinx.coroutines.delay

@Composable
fun ExerciseCircleView(
    amount: Int,
    textBelow: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center,

        ) {
        Canvas(
            modifier = Modifier
                .width(216.dp)
                .height(216.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = Violet,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension / 2,
                style = Stroke(10F)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = amount.toString(),
                style = MaterialTheme.typography.h1,
                color = Color.White,
            )
            Text(
                text = textBelow,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = Montserrat,
                    color = Color.White
                )
            )
        }
    }

}

@Composable
fun Timer(
    text: String,
    totalTime: Int,
    isTimerRunning: Boolean,
    onFinish: () -> Unit,
) {


    var progressValue by remember {
        mutableStateOf(1f)
    }

    var currentTime by remember {
        mutableStateOf(totalTime * 1000L)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (isTimerRunning) {
            if (currentTime > 0) {
                delay(100L)
                currentTime -= 100L
                progressValue = currentTime / (totalTime * 1000L).toFloat()
            } else {
                onFinish()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            progress = progressValue,
            modifier = Modifier
                .size(216.dp),
            color = Violet,
            strokeWidth = 4.dp
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (currentTime / 1000L).toString(),
                style = MaterialTheme.typography.h1,
                color = Color.White,
            )
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = Montserrat,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun SuccessCircleView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center,

        ) {
        Canvas(
            modifier = Modifier
                .width(216.dp)
                .height(216.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = Violet,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension / 2,
                style = Stroke(10F)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.big_tick_icon),
            contentDescription = null
        )
    }

}

@Composable
fun UnSuccessCircleView(string: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center,

        ) {
        Canvas(
            modifier = Modifier
                .width(216.dp)
                .height(216.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = Violet,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension / 2,
                style = Stroke(10F)
            )
        }
        Text(
            text = string,
            modifier = Modifier.align(Alignment.Center).size(210.dp),
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
    }

}

