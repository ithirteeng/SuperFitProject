package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.entity.ExerciseEntity
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.GrayWhite

@Composable
fun BaseCard(
    imageId: Int,
    SecondPart: @Composable (modifier: Modifier) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .defaultMinSize(minHeight = 114.dp)
            .height(IntrinsicSize.Min),
        backgroundColor = GrayDark,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = R.string.background_content_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .drawWithCache {
                        val gradient = Brush.horizontalGradient(
                            colors = listOf(Color.Transparent, GrayDark),
                            startX = size.width * 0.7f,
                            endX = size.width
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.SrcOver)
                        }
                    },
            )
            SecondPart(modifier = Modifier.weight(1f))
        }
    }

}

@Composable
fun ExerciseCard(
    imageId: Int,
    exerciseEntity: ExerciseEntity,
) {
    BaseCard(imageId = imageId) { modifier ->
        Column(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
        ) {
            Text(
                text = stringResource(id = exerciseEntity.nameId),
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(id = exerciseEntity.descriptionId),
                style = MaterialTheme.typography.body1,
                color = GrayWhite
            )
        }
    }
}