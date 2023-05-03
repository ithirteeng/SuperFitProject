package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ithirteeng.superfitproject.R

@Preview
@Composable
fun ImageHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.exercise_image),
            contentDescription = stringResource(id = R.string.background_content_description),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = R.string.super_fit),
            style = MaterialTheme.typography.h4,
            color = Color.White

        )
    }

}