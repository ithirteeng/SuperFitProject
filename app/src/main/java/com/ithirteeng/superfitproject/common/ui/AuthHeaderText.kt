package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.R

@Composable
fun AuthHeaderText() {
    Text(
        modifier = Modifier
            .padding(top = 68.dp),
        text = stringResource(id = R.string.super_fit),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.primary
    )
}