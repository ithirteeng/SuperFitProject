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
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.statistics.domain.entity.BodyParamsEntity

@Composable
fun WeightGraph(weightHistory: List<BodyParamsEntity>?) {
    if (weightHistory != null) {
        val yStep = 20
        val xValues = weightHistory.flatMap { listOf(it.date) }
        val yValues = (0..6).map { (it + 1) * yStep }
        val points = weightHistory.flatMap { listOf(it.weight.toFloat()) }

        val paddingSpace = 20.dp

        WeightGraphView(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(top = 25.dp, end = 25.dp)
                .height(240.dp)
                .width(points.size * 50.dp),
            xValues = xValues,
            yValues = yValues,
            points = points,
            space = paddingSpace,
            yStep = yStep,
            gridColor = Color.White,
            lineColor = Violet,
            pointColor = Violet,
        )
    }
}