package com.ithirteeng.superfitproject.trainprogress.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.Violet

class TrainProgressScreen : Screen {

    private companion object {
        val defaultScreen = Pair(360.0, 640.0)

        val pushUpsCircle = Pair(137.0, 155.0)
        val pushUpsBottomLeft = Pair(139.5, 153.0)
        val pushUpsTopLeft = Pair(166.5, 126.0)
        val pushUpsTopRight = Pair(294.5, 126.0)

        val plankCircle = Pair(129.0, 224.0)
        val plankLeft = Pair(132.5, 224.0)
        val plankRight = Pair(302.0, 224.0)

        val crunchCircle = Pair(98.0, 268.0)
        val crunchTopLeft = Pair(101.0, 271.0)
        val crunchBottomLeft = Pair(153.5, 323.5)
        val crunchBottomRight = Pair(295.5, 323.5)

        val squatsCircle = Pair(127.0, 421.0)
        val squatsTopLeft = Pair(129.0, 424.0)
        val squatsBottomLeft = Pair(159.0, 453.5)
        val squatsBottomRight = Pair(292.0, 453.5)

        val runningCircle = Pair(127.0, 593.0)
        val runningBottomLeft = Pair(130.0, 592.0)
        val runningTopLeft = Pair(159.5, 563.0)
        val runningTopRight = Pair(292.0, 563.0)
    }

    @Composable
    override fun Content() {
        TrainProgressView()
    }

    @Composable
    private fun TrainProgressView() {
        var imageSize by remember {
            mutableStateOf(Pair(0, 0))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { imageSize = Pair(it.size.width, it.size.height) },
                painter = painterResource(id = R.drawable.train_progress_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawPushUps(imageSize = imageSize)
                        drawPlank(imageSize = imageSize)
                        drawCrunch(imageSize = imageSize)
                        drawSquats(imageSize = imageSize)
                        drawRunning(imageSize = imageSize)
                    },
            )
        }
    }

    private fun DrawScope.drawPushUps(imageSize: Pair<Int, Int>) {
        val cordsCircle = countOffset(imageSize, pushUpsCircle)
        val cordsBottomLeft = countOffset(imageSize, pushUpsBottomLeft)
        val cordsTopLeft = countOffset(imageSize, pushUpsTopLeft)
        val cordsTopRight = countOffset(imageSize, pushUpsTopRight)
        drawCircle(
            color = Violet,
            center = cordsCircle,
            style = Stroke(2.dp.toPx()),
            radius = 4.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsBottomLeft,
            end = cordsTopLeft,
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsTopLeft,
            end = cordsTopRight,
            strokeWidth = 2.dp.toPx()
        )
    }

    private fun DrawScope.drawPlank(imageSize: Pair<Int, Int>) {
        val cordsCircle = countOffset(imageSize, plankCircle)
        val cordsLeft = countOffset(imageSize, plankLeft)
        val cordsRight = countOffset(imageSize, plankRight)
        drawCircle(
            color = Violet,
            center = cordsCircle,
            style = Stroke(2.dp.toPx()),
            radius = 4.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsLeft,
            end = cordsRight,
            strokeWidth = 2.dp.toPx()
        )
    }

    private fun DrawScope.drawCrunch(imageSize: Pair<Int, Int>) {
        val cordsCircle = countOffset(imageSize, crunchCircle)
        val cordsTopLeft = countOffset(imageSize, crunchTopLeft)
        val cordsBottomLeft = countOffset(imageSize, crunchBottomLeft)
        val cordsBottomRight = countOffset(imageSize, crunchBottomRight)
        drawCircle(
            color = Violet,
            center = cordsCircle,
            style = Stroke(2.dp.toPx()),
            radius = 4.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsTopLeft,
            end = cordsBottomLeft,
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsBottomLeft,
            end = cordsBottomRight,
            strokeWidth = 2.dp.toPx()
        )
    }

    private fun DrawScope.drawSquats(imageSize: Pair<Int, Int>) {
        val cordsCircle = countOffset(imageSize, squatsCircle)
        val cordsTopLeft = countOffset(imageSize, squatsTopLeft)
        val cordsBottomLeft = countOffset(imageSize, squatsBottomLeft)
        val cordsBottomRight = countOffset(imageSize, squatsBottomRight)
        drawCircle(
            color = Violet,
            center = cordsCircle,
            style = Stroke(2.dp.toPx()),
            radius = 4.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsTopLeft,
            end = cordsBottomLeft,
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsBottomLeft,
            end = cordsBottomRight,
            strokeWidth = 2.dp.toPx()
        )
    }

    private fun DrawScope.drawRunning(imageSize: Pair<Int, Int>) {
        val cordsCircle = countOffset(imageSize, runningCircle)
        val cordsBottomLeft = countOffset(imageSize, runningBottomLeft)
        val cordsTopLeft = countOffset(imageSize, runningTopLeft)
        val cordsTopRight = countOffset(imageSize, runningTopRight)
        drawCircle(
            color = Violet,
            center = cordsCircle,
            style = Stroke(2.dp.toPx()),
            radius = 4.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsBottomLeft,
            end = cordsTopLeft,
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = Violet,
            start = cordsTopLeft,
            end = cordsTopRight,
            strokeWidth = 2.dp.toPx()
        )
    }

    private fun countOffset(
        screenSize: Pair<Int, Int>,
        point: Pair<Double, Double>,
    ): Offset {
        val kX = screenSize.first / defaultScreen.first
        val ky = screenSize.second / defaultScreen.second
        return Offset(
            (point.first * kX.toFloat()).toFloat(),
            (point.second * ky.toFloat()).toFloat()
        )
    }

}