package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.Montserrat
import com.ithirteeng.superfitproject.common.ui.theme.Violet

@Composable
fun ExerciseView(
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