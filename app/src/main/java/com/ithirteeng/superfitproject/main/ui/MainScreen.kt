package com.ithirteeng.superfitproject.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.ithirteeng.superfitproject.common.ui.ImageHeader

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MainScreenView()
    }

    @Composable
    private fun MainScreenView() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ImageHeader()
        }
    }
}