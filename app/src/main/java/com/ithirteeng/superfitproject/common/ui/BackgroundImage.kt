package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ithirteeng.superfitproject.R

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.pretty_background),
        contentDescription = stringResource(id = R.string.background_content_description),
        modifier = Modifier
            .fillMaxSize(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}