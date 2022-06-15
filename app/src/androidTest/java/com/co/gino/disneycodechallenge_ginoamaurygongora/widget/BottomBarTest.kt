package com.co.gino.disneycodechallenge_ginoamaurygongora.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.theme.DisneyCodeChallenge_GinoAmauryGongoraTheme
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget.BottomBarMain
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget.HomeAppBar
import com.co.gino.domain.builder.GuestBuilder
import com.co.gino.domain.models.Guest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkBottomBar_whenSnackbarIsInvisible_isDisplayed(){
        //Arrange
        val guests:SnapshotStateList<Guest> = SnapshotStateList()
        guests.add(GuestBuilder.getGuest())
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BottomBarMain(guests = guests,false,{})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithText("Continue").assertIsDisplayed()
    }
    @Test
    fun checkBottomBar_whenSnackbarIsVisible_isDisplayed(){
        //Arrange
        val guests:SnapshotStateList<Guest> = SnapshotStateList()
        guests.add(GuestBuilder.getGuest())
        //Act
        composeTestRule.setContent {
            DisneyCodeChallenge_GinoAmauryGongoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BottomBarMain(guests = guests,true,{})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithText("Reservation Needed").assertIsDisplayed()
    }
}