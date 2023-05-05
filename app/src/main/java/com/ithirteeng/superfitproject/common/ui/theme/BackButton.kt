package com.ithirteeng.superfitproject.common.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.R

@Composable
fun BackButton(onButtonClick: () -> Unit) {
    IconButton(
        onClick = onButtonClick,
        modifier = Modifier
            .padding(start = 20.dp, top = 40.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back_icon),
            tint = Color.White,
            contentDescription = stringResource(id = R.string.arrow_content_description)
        )
    }
}