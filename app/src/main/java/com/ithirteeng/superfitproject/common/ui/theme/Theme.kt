package com.ithirteeng.superfitproject.common.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val SuperFitColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = GrayDark,
    onSecondary = Color.White,
    secondaryVariant = GrayLight,
    surface = Violet,
    onSurface = Color.White
)

@Composable
fun SuperFitProjectTheme(
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colors = SuperFitColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}