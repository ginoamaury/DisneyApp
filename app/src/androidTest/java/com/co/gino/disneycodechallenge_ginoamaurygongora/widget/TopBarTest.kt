package com.co.gino.disneycodechallenge_ginoamaurygongora.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.theme.DisneyCodeChallenge_GinoAmauryGongoraTheme
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget.HomeAppBar
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkTopBar_whenMainScreen_isDisplayed(){
        //Arrange
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeAppBar(MaterialTheme.colors.background)
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithText("Select Guests").assertIsDisplayed()
    }
}