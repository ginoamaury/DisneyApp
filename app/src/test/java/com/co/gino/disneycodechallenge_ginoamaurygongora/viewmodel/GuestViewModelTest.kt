package com.co.gino.disneycodechallenge_ginoamaurygongora.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.co.gino.disneycodechallenge_ginoamaurygongora.CoroutineTestRule
import com.co.gino.domain.builder.GuestBuilder
import com.co.gino.domain.models.Guest
import com.co.gino.domain.services.GetGuestsService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(manifest = Config.NONE)
class GuestViewModelTest {
    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var guestsService: GetGuestsService

    private lateinit var guestViewModel: GuestsViewModel


    @Before
    fun start(){
        guestViewModel = GuestsViewModel(getGuestsService = guestsService, ioDispatcher = coroutineTestRule.testDispatcher)
    }

    @Test
    fun getGuests_whenGetGuestServiceWasCalled_okResponse() {
        runBlocking {
            Mockito.verify(guestsService, Mockito.times(1)).invoke()
        }
    }

    @Test
    fun getGuests_whenGetGuests_exceptionResponse(){
        runBlocking {
            //Arrange
            Mockito.`when`(guestsService.invoke()).thenReturn(null)
            //Act
            guestViewModel.getGuests()
            //Assert
            Assert.assertTrue(guestViewModel.uiState.value.error)
        }
    }

    @Test
    fun getGuests_whenGetGuests_okResponse(){
        runBlocking {
            //Arrange
            Mockito.`when`(guestsService.invoke()).thenReturn(GuestBuilder.getFlowListGuest())
            //Act
            guestViewModel.getGuests()
            //Assert
            Assert.assertEquals(guestViewModel.uiState.value.success[0].name, GuestBuilder.getGuest().name)
        }
    }
}