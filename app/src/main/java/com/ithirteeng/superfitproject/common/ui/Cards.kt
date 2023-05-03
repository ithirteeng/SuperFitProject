package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark

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
            .height(IntrinsicSize.Min),
        contentColor = GrayDark,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = R.string.background_content_description),
                contentScale = ContentScale.Crop,

                )
            SecondPart(modifier = Modifier.weight(1f))
        }
    }

}