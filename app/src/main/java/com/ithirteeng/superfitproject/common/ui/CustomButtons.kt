package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.theme.Violet

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

@Composable
fun FinishExerciseButton(onButtonClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Violet),
        onClick = {
            onButtonClick()
        },
        contentPadding = PaddingValues(vertical = 13.dp)
    ) {
        Text(
            text = stringResource(id = R.string.finish),
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
    }
}