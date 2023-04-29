package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.common.entity.ErrorEntity

@Composable
fun ErrorAlertDialog(
    errorEntity: ErrorEntity,
    onDismiss: () -> Unit,
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
                openDialog.value = false
            },
            buttons = {
                Box(
                    modifier = Modifier
                        .wrapContentSize(),
                ) {
                    Text(
                        text = stringResource(id = errorEntity.messageId),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.secondary
        )
    }
}