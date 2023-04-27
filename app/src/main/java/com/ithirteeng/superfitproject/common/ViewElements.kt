package com.ithirteeng.superfitproject.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.common.theme.DividerLight

@Composable
fun MyTextField(
    placeHolderString: String,
    value: String,
    onValueChanged: (value: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            value = value,
            onValueChange = { onValueChanged(it) },
            textStyle = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(Color.White),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeHolderString,
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                }
                innerTextField()
            }
        )

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = DividerLight,
            thickness = 2.dp
        )
    }
}