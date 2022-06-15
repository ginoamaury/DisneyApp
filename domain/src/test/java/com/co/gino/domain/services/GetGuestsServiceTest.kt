package com.co.gino.domain.services

import com.co.gino.domain.builder.GuestBuilder
import com.co.gino.domain.models.Guest
import com.co.gino.domain.repositories.GuestProxy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGuestsServiceTest {
    @Mock
    lateinit var guestProxy : GuestProxy

    @InjectMocks
    lateinit var guestsService: GetGuestsService

    private val flowGuest: Flow<List<Guest>> = GuestBuilder.getFlowListGuest()

    @Test
    fun guestsService_whenIsInvoked_guestsResponse(){
        runBlocking {
            //Arrange
            Mockito.`when`(guestProxy.getAllGuests()).thenReturn(flowGuest)
            //Act
            val result = guestsService.invoke()
            //Assert
            Mockito.verify(guestProxy, Mockito.times(1)).getAllGuests()
            Assert.assertEquals(flowGuest,result)
        }

    }
}