package com.co.gino.disneycodechallenge_ginoamaurygongora.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.view.MainScreen
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.view.SplashScreen

private const val MAIN_SCREEN_ROUTE = "main_screen"

@Composable
fun Navigation(isLoading: (Boolean) -> Unit, loadingSplashScreen: Boolean) {
    if (loadingSplashScreen) {
        SplashScreen(isLoading = isLoading)
    } else {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = MAIN_SCREEN_ROUTE) {
            composable(MAIN_SCREEN_ROUTE) {
                MainScreen()
            }
        }
    }
}