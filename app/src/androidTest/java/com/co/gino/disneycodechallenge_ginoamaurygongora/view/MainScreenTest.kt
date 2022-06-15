package com.co.gino.disneycodechallenge_ginoamaurygongora.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.theme.DisneyCodeChallenge_GinoAmauryGongoraTheme
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.view.HomeContent
import com.co.gino.domain.builder.GuestBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val guests = GuestBuilder.getListGuest()

    @Test
    fun showMainScreen_whenIsLoading_isDisplayed(){
        //Arrange
        val loading = true
        val error = false
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeContent(guests,loading = loading,error = error, checkGuest = {_,_ ->})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }

    @Test
    fun showMainScreen_whenIsLoaded_isDisplayed(){
        //Arrange
        val loading = false
        val error = false
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeContent(guests,loading = loading,error = error, checkGuest = {_,_ ->})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag("loaded").assertIsDisplayed()
    }

    @Test
    fun showMainScreen_whenHasError_isDisplayed(){
        //Arrange
        val loading = false
        val error = true
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeContent(guests,loading = loading,error = error, checkGuest = {_,_ ->})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag("error").assertIsDisplayed()
    }
}