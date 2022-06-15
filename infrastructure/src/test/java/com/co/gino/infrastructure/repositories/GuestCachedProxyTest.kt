package com.co.gino.infrastructure.repositories

import com.co.gino.domain.builder.GuestBuilder
import com.co.gino.domain.models.Guest
import com.co.gino.domain.repositories.GuestLocalRepository
import com.co.gino.domain.repositories.GuestRemoteRepository
import com.co.gino.infrastructure.guests.repositories.GuestsCachedProxy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class GuestCachedProxyTest {
    @Mock
    private lateinit var localRepository: GuestLocalRepository

    @Mock
    private lateinit var remoteRepository: GuestRemoteRepository

    @InjectMocks
    private lateinit var guestCachedProxy: GuestsCachedProxy

    private val guests = GuestBuilder.getListGuest()
    private val flowGuests: Flow<List<Guest>> = flow { emit(guests) }

    @Test
    fun getGuests_whenIsLocalRepository_guestsResult() {
        runBlocking {
            //Arrange
            Mockito.`when`(localRepository.getAllGuests()).thenReturn(flowGuests)
            //Act
            val result = guestCachedProxy.getAllGuests()
            //Assert
            Mockito.verify(localRepository, Mockito.times(1)).getAllGuests()
            Assert.assertEquals(flowGuests, result)
        }
    }

    @Test
    fun getGuests_whenLocalRepositoryIsEmpty_guestsResultFromRemote() {
        runBlocking {
            //Arrange
            Mockito.`when`(localRepository.isEmpty()).thenReturn(true)
            Mockito.`when`(remoteRepository.getAllGuests()).thenReturn(flowGuests)
            //Act
            val result = guestCachedProxy.getAllGuests()
            //Assert
            Mockito.verify(localRepository, Mockito.times(1)).isEmpty()
            Mockito.verify(localRepository, Mockito.times(0)).getAllGuests()
            Mockito.verify(remoteRepository, Mockito.times(1)).getAllGuests()
            Mockito.verify(localRepository, Mockito.times(1)).insertGuests(guests = guests)
            Assert.assertEquals(flowGuests, result)
        }
    }
}