package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.common.ui.theme.AlertTextColor
import com.ithirteeng.superfitproject.common.ui.theme.GrayLight
import com.ithirteeng.superfitproject.common.ui.theme.PlaceHolder
import com.ithirteeng.superfitproject.common.ui.theme.Violet

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


@Composable
fun ChangeParamsAlertDialog(
    onDismiss: () -> Unit,
    onChangeButtonClick: () -> Unit,
    header: String,
    textFieldLabel: String,
    textFieldPlaceHolder: String,
    textFieldValue: String,
    onTextChanged: (value: String) -> Unit,
) {
    AlertDialog(
        backgroundColor = GrayLight,
        onDismissRequest = {
            onDismiss()
        },
        buttons = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AlertTextField(
                    label = textFieldLabel,
                    placeholder = textFieldPlaceHolder,
                    text = textFieldValue,
                    onTextChanged = onTextChanged
                )
                AlertBottomButtons(
                    firstButtonText = stringResource(id = R.string.cancel),
                    secondButtonText = stringResource(id = R.string.change),
                    onFirstButtonClick = onDismiss,
                    onSecondButtonClick = onChangeButtonClick
                )
            }
        },
        title = {
            AlertHeaderText(text = header)
        }
    )
}

@Composable
fun TextAlertDialog(
    onLaterButtonClick: () -> Unit,
    onGoButtonClick: () -> Unit,
    header: String,
    text: String
) {
    AlertDialog(
        backgroundColor = GrayLight,
        onDismissRequest = {
            onLaterButtonClick()
        },
        buttons = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    text = text,
                    style = TextStyle(
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = AlertTextColor
                    )
                )
                AlertBottomButtons(
                    firstButtonText = stringResource(id = R.string.later),
                    secondButtonText = stringResource(id = R.string.go),
                    onFirstButtonClick = onLaterButtonClick,
                    onSecondButtonClick = onGoButtonClick
                )
            }
        },
        title = {
            AlertHeaderText(text = header)
        }
    )
}

@Composable
private fun AlertTextField(
    label: String,
    placeholder: String,
    text: String,
    onTextChanged: (value: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                    focusManager.clearFocus(true)
                }
                false
            },
        value = text,
        onValueChange = {
            if (it.length <= 4) {
                if (
                    it.isNotEmpty() &&
                    !it.contains(Regex("\\s*\n*")) &&
                    !it.matches(Regex("\\D"))
                ) {
                    onTextChanged(it)
                } else {
                    onTextChanged(it.replace(Regex("\\s*\n*\\D*"), ""))
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus(true) }
        ),
        label = { Text(text = label, color = Violet) },
        placeholder = { Text(text = placeholder, color = PlaceHolder, fontSize = 16.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Violet,
            cursorColor = Violet
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.White
        ),


        )
}

@Composable
private fun AlertBottomButtons(
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        AlertDialogTextButton(text = firstButtonText.uppercase()) {
            onFirstButtonClick()
        }
        AlertDialogTextButton(text = secondButtonText.uppercase()) {
            onSecondButtonClick()
        }
    }
}

@Composable
private fun AlertDialogTextButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Violet
        ),
        modifier = Modifier
            .padding(end = 20.dp, start = 4.dp, bottom = 20.dp, top = 24.dp)
            .clickable { onClick() }
    )
}

@Composable
private fun AlertHeaderText(text: String) {
    Text(
        text = stringResource(id = R.string.change_your) + " " + text,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color.White
        ),
        modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
    )
}