package com.co.gino.disneycodechallenge_ginoamaurygongora.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.gino.disneycodechallenge_ginoamaurygongora.navigation.Navigation
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.theme.DisneyCodeChallenge_GinoAmauryGongoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkSplashScreen_whenAppStart_isDisplayed(){
        //Arrange
        val showSplashScreen = true
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation({},showSplashScreen)
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithContentDescription("Disney").assertIsDisplayed()
    }
}