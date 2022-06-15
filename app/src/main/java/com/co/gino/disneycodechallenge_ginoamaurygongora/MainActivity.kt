package com.co.gino.disneycodechallenge_ginoamaurygongora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.co.gino.disneycodechallenge_ginoamaurygongora.navigation.Navigation
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.theme.DisneyCodeChallenge_GinoAmauryGongoraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var loadingSplashScreen by remember {
                        mutableStateOf(true)
                    }
                    Navigation(
                        isLoading = { loadingSplashScreen = it },
                        loadingSplashScreen = loadingSplashScreen
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DisneyCodeChallenge_GinoAmauryGongoraTheme {
        Surface(color = MaterialTheme.colors.background) {
            var loadingSplashScreen by remember {
                mutableStateOf(false)
            }
            Navigation(
                isLoading = { loadingSplashScreen = it },
                loadingSplashScreen = loadingSplashScreen
            )
        }
    }
}