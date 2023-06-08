package com.ithirteeng.superfitproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import com.ithirteeng.superfitproject.common.ui.theme.SuperFitProjectTheme
import com.ithirteeng.superfitproject.splash.ui.SplashScreen

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SuperFitProjectTheme {
                CompositionLocalProvider(
                    LocalOverscrollConfiguration provides null
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding(),
                        color = MaterialTheme.colors.background
                    ) {
                        Navigator(screen = SplashScreen())
                    }
                }
            }
        }
    }

}
