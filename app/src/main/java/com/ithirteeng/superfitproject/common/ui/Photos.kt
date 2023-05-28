package com.ithirteeng.superfitproject.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.photos.domain.model.ImageModel
import com.ithirteeng.superfitproject.common.ui.theme.Violet


@Composable
fun MyBodyImages(firstImage: ImageModel?, secondImage: ImageModel?, onAddButtonClick: () -> Unit) {
    Box(modifier = Modifier.padding(vertical = 16.dp)) {
        Row {
            MyBodyImage(
                imageModel = firstImage,
                shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                modifier = Modifier.weight(0.5f)
            )
            Spacer(
                modifier = Modifier
                    .background(Violet)
                    .width(4.dp)
            )
            MyBodyImage(
                imageModel = secondImage,
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                modifier = Modifier.weight(0.5f)
            )
        }
        AddImageButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        ) {
            onAddButtonClick()
        }
    }
}

@Composable
private fun AddImageButton(modifier: Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = modifier.size(24.dp)) {
        Box(
            modifier = Modifier.background(Color.White, RoundedCornerShape(100.dp))
        ) {
            Image(
                modifier = Modifier
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.add_image_icon),
                contentDescription = null
            )
        }
    }

}

@Composable
private fun MyBodyImage(imageModel: ImageModel?, shape: Shape, modifier: Modifier) {
    var date = ""
    var bitmap = ImageBitmap.imageResource(id = R.drawable.image_placeholder)
    if (imageModel != null) {
        date = imageModel.date
        bitmap = imageModel.bitmap.asImageBitmap()
    }
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .height(216.dp)
                .fillMaxWidth()
                .clip(shape),
            bitmap = bitmap,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        DateView(
            date = date,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart)
        )
    }

}

@Composable
private fun DateView(date: String, modifier: Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .background(Violet, RoundedCornerShape(30.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp),
            text = date,
            color = Color.White,
            style = MaterialTheme.typography.body1
        )
    }

}