package com.ithirteeng.superfitproject.mybody.ui

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider.getUriForFile
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.ui.ChangeParamsAlertDialog
import com.ithirteeng.superfitproject.common.ui.ErrorAlertDialog
import com.ithirteeng.superfitproject.common.ui.MyBodyButton
import com.ithirteeng.superfitproject.common.ui.MyBodyImages
import com.ithirteeng.superfitproject.common.ui.PickImageDialog
import com.ithirteeng.superfitproject.common.ui.theme.GrayDark
import com.ithirteeng.superfitproject.common.ui.theme.GrayWhite
import com.ithirteeng.superfitproject.common.ui.theme.Violet
import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenIntent
import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenViewModel
import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType
import org.koin.androidx.compose.koinViewModel
import java.io.File


class MyBodyScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: MyBodyScreenViewModel = koinViewModel()
        viewModel.accept(MyBodyScreenIntent.Initial)
        val context = LocalContext.current
        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = {
                it?.let { uri ->
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val image = inputStream?.readBytes()
                    inputStream?.close()
                    viewModel.accept(MyBodyScreenIntent.PickPhoto(image))
                }
            }
        )
        val uri = getImageUri(context)
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = {
                if (it) {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val image = inputStream?.readBytes()
                    inputStream?.close()
                    viewModel.accept(MyBodyScreenIntent.PickPhoto(image))
                }
            })
        MyBodyScreenView(viewModel = viewModel, galleryLauncher, cameraLauncher, uri)
    }

    @Composable
    fun MyBodyScreenView(
        viewModel: MyBodyScreenViewModel,
        galleryLauncher: ManagedActivityResultLauncher<String, Uri?>,
        cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
        uri: Uri,
    ) {
        val state = viewModel.state.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayDark),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .statusBarsPadding()
            ) {
                item { HeaderText(text = stringResource(id = R.string.my_body)) }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(48.dp)
                    ) {
                        ParamView(text = state.weight) {
                            viewModel.accept(
                                MyBodyScreenIntent.OpenAlertDialog(
                                    AlertDialogType.WEIGHT
                                )
                            )
                        }
                        ParamView(text = state.height) {
                            viewModel.accept(
                                MyBodyScreenIntent.OpenAlertDialog(
                                    AlertDialogType.HEIGHT
                                )
                            )
                        }
                    }
                }
                item { HeaderText(text = stringResource(id = R.string.my_progress)) }

                item {
                    MyBodyImages(firstImage = state.firstImage, secondImage = state.secondImage) {
                        viewModel.accept(MyBodyScreenIntent.AddPictureButtonClick)
                    }
                }

                item {
                    MyBodyButton(text = stringResource(id = R.string.train_progress)) {
                        //todo on train button click
                    }
                }

                item {
                    MyBodyButton(text = stringResource(id = R.string.statistics)) {
                        //todo on statistics button click
                    }
                }
            }

            if (state.error != null) {
                ErrorAlertDialog(errorEntity = state.error) {
                    viewModel.accept(MyBodyScreenIntent.DismissError)
                }
            } else if (state.isAlertDialogOpened) {
                val label = if (state.alertDialogType == AlertDialogType.WEIGHT) {
                    stringResource(id = R.string.weight)
                } else {
                    stringResource(id = R.string.height)
                }
                ChangeParamsAlertDialog(
                    onDismiss = {
                        viewModel.accept(MyBodyScreenIntent.CloseAlertDialog)
                    },
                    onChangeButtonClick = {
                        if (state.alertDialogType == AlertDialogType.WEIGHT) {
                            viewModel.accept(MyBodyScreenIntent.ChangeWeight)
                        } else {
                            viewModel.accept(MyBodyScreenIntent.ChangeHeight)
                        }
                    },
                    header = state.alertDialogType.type,
                    textFieldLabel = label,
                    textFieldPlaceHolder = stringResource(id = R.string.new_value),
                    textFieldValue = state.alertTextFieldValue,
                    onTextChanged = { viewModel.accept(MyBodyScreenIntent.AlertTextFieldChange(it)) }
                )
            } else if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize(),
                    color = Violet
                )
            } else if (state.isPhotoPickerDialogOpened) {
                PickImageDialog(
                    onPhotoButtonClick = {
                        cameraLauncher.launch(uri)
                    },
                    onGalleryButtonClick = {
                        galleryLauncher.launch("image/*")
                    },
                    onCancelButtonClick = {
                        viewModel.accept(MyBodyScreenIntent.ClosePickImageDialog)
                    }
                )
            }
        }

    }

    private fun getImageUri(context: Context): Uri {
        val directory = File(context.cacheDir, "images")
        directory.mkdirs()
        val file = File.createTempFile(
            "selected_image",
            ".jpg",
            directory,
        )
        val authority = context.packageName + ".provider"
        return getUriForFile(context, authority, file)
    }

    @Composable
    private fun HeaderText(text: String) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = text,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Start,
            color = Color.White
        )
    }

    @Composable
    private fun RowScope.ParamView(text: String, onEditButtonClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h4,
                color = Color.White,
            )
            Text(
                text = stringResource(id = R.string.edit),
                style = MaterialTheme.typography.body1,
                color = GrayWhite,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {
                        onEditButtonClick()
                    }
            )
        }
    }


}