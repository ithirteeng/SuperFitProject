package com.ithirteeng.superfitproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen

class MainScreen : Screen {
    @Composable
    override fun Content() {
        Text(modifier = Modifier.fillMaxSize(), text = "HUI", textAlign = TextAlign.Center)
    }
}