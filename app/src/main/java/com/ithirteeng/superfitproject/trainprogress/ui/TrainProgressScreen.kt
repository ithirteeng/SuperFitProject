package com.ithirteeng.superfitproject.trainprogress.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.BackButton
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.trainprogress.presentation.TrainProgressIntent
import com.ithirteeng.superfitproject.trainprogress.presentation.TrainProgressViewModel
import com.ithirteeng.superfitproject.trainprogress.presentation.model.ProgressIndicator
import com.ithirteeng.superfitproject.trainprogress.presentation.model.TrainStatModel
import org.koin.androidx.compose.koinViewModel

class TrainProgressScreen : Screen {

    private companion object {
        val defaultScreen = Pair(360.0, 640.0)

        val pushUpsHeader = Pair(175.0, 105.0)
        val pushUpsStats = Pair(175.0, 130.0)
        val pushUpsCircle = Pair(137.0, 155.0)
        val pushUpsBottomLeft = Pair(139.5, 153.0)
        val pushUpsTopLeft = Pair(166.5, 126.0)
        val pushUpsTopRight = Pair(294.5, 126.0)

        val plankHeader = Pair(161.0, 203.0)
        val plankStats = Pair(161.0, 232.0)
        val plankCircle = Pair(129.0, 224.0)
        val plankLeft = Pair(132.5, 224.0)
        val plankRight = Pair(302.0, 224.0)

        val crunchHeader = Pair(163.0, 301.0)
        val crunchStats = Pair(163.0, 328.0)
        val crunchCircle = Pair(98.0, 268.0)
        val crunchTopLeft = Pair(101.0, 271.0)
        val crunchBottomLeft = Pair(153.5, 323.5)
        val crunchBottomRight = Pair(295.5, 323.5)

        val squatsHeader = Pair(167.0, 431.0)
        val squatsStats = Pair(167.0, 458.0)
        val squatsCircle = Pair(127.0, 421.0)
        val squatsTopLeft = Pair(129.0, 424.0)
        val squatsBottomLeft = Pair(159.0, 453.5)
        val squatsBottomRight = Pair(292.0, 453.5)

        val runningHeader = Pair(167.5, 540.0)
        val runningStats = Pair(167.5, 567.0)
        val runningCircle = Pair(127.0, 593.0)
        val runningBottomLeft = Pair(130.0, 592.0)
        val runningTopLeft = Pair(159.5, 563.0)
        val runningTopRight = Pair(292.0, 563.0)
    }

    @Composable
    override fun Content() {
        val viewModel: TrainProgressViewModel = koinViewModel()
        viewModel.accept(TrainProgressIntent.Initial)
        TrainProgressView(viewModel)
    }

    @Composable
    private fun TrainProgressView(viewModel: TrainProgressViewModel) {
        val state = viewModel.state.collectAsState()

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
            ) {
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentSize(),
                        color = Color.White
                    )
                } else if (state.value.error != null) {
                    ErrorAlertDialog(errorEntity = state.value.error!!) {
                        viewModel.accept(TrainProgressIntent.DismissError)
                    }
                } else if (state.value.ifBackButtonPressed) {
                    LocalNavigator.currentOrThrow.pop()
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BackButton {
                        viewModel.accept(TrainProgressIntent.BackButtonClick)
                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 40.dp),
                        text = stringResource(id = R.string.train_progress),
                        color = Color.White,
                        style = MaterialTheme.typography.h5
                    )
                }
                TrainText(
                    headerText = stringResource(id = R.string.push_ups),
                    headerCords = pushUpsHeader,
                    trainStat = state.value.pushUpsTrain,
                    trainInfoCords = pushUpsStats,
                    imageSize = imageSize
                )
                TrainText(
                    headerText = stringResource(id = R.string.plank),
                    headerCords = plankHeader,
                    trainStat = state.value.plankTrain,
                    trainInfoCords = plankStats,
                    imageSize = imageSize
                )
                TrainText(
                    headerText = stringResource(id = R.string.crunch),
                    headerCords = crunchHeader,
                    trainStat = state.value.crunchTrain,
                    trainInfoCords = crunchStats,
                    imageSize = imageSize
                )
                TrainText(
                    headerText = stringResource(id = R.string.squats),
                    headerCords = squatsHeader,
                    trainStat = state.value.squatsTrain,
                    trainInfoCords = squatsStats,
                    imageSize = imageSize
                )
                TrainText(
                    headerText = stringResource(id = R.string.running),
                    headerCords = runningHeader,
                    trainStat = state.value.runningTrain,
                    trainInfoCords = runningStats,
                    imageSize = imageSize
                )
            }
        }
    }

    @Composable
    private fun TrainText(
        headerText: String,
        headerCords: Pair<Double, Double>,
        trainStat: TrainStatModel,
        trainInfoCords: Pair<Double, Double>,
        imageSize: Pair<Int, Int>,
    ) {
        HeaderText(text = headerText, imageSize = imageSize, cords = headerCords)
        TrainInfo(trainStat = trainStat, imageSize = imageSize, cords = trainInfoCords)
    }

    @Composable
    private fun TrainInfo(
        trainStat: TrainStatModel,
        imageSize: Pair<Int, Int>,
        cords: Pair<Double, Double>,
    ) {
        val correctCords = countOffset(imageSize, cords)
        Column(
            modifier = Modifier
                .offset(
                    x = with(LocalDensity.current) { correctCords.x.toDp() },
                    y = with(LocalDensity.current) { correctCords.y.toDp() },
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.last_train),
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
                Text(
                    text = trainStat.amount.toString(),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.progress),
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
                Text(
                    text = trainStat.progress.toString(),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White
                )
                var painter: Painter? = painterResource(id = R.drawable.descending_icon)
                if (trainStat.indicator == ProgressIndicator.ASCENDING) {
                    painter = painterResource(id = R.drawable.ascending_icon)
                } else if (trainStat.indicator == ProgressIndicator.NULL) {
                    painter = null
                }
                if (painter != null) {
                    Image(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        painter = painter,
                        contentDescription = null
                    )
                }
            }

        }
    }

    @Composable
    private fun HeaderText(
        text: String,
        imageSize: Pair<Int, Int>,
        cords: Pair<Double, Double>,
    ) {
        val correctCords = countOffset(imageSize, cords)
        Text(
            text = text,
            modifier = Modifier
                .offset(
                    x = with(LocalDensity.current) { correctCords.x.toDp() },
                    y = with(LocalDensity.current) { correctCords.y.toDp() },
                ),
            style = MaterialTheme.typography.subtitle2
        )
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