package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.common.ui.theme.DividerLight

@Composable
fun MyTextField(
    placeHolderString: String,
    value: String,
    onValueChanged: (value: String) -> Unit,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    forbiddenChars: Regex = Regex(""),
    maxLength: Int = 300,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp)
    ) {
        val focusManager = LocalFocusManager.current

        BasicTextField(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 3.dp)
                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                        focusManager.clearFocus(true)
                    }
                    false
                },
            visualTransformation = visualTransformation,
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    if (
                        it.isNotEmpty() &&
                        !it.contains(Regex("\\s*\n*")) &&
                        !it.contains(forbiddenChars)
                    ) {
                        onValueChanged(it)
                    } else {
                        val string = it.replace(Regex("\\s*\n*"), "")
                        onValueChanged(string.replace(forbiddenChars, ""))
                    }
                }
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.subtitle1,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) }
            ),
            cursorBrush = SolidColor(Color.White),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier) {
                    if (value.isEmpty() || value.isBlank()) {
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