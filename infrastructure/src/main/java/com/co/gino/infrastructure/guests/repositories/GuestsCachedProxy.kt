package com.co.gino.infrastructure.guests.repositories

import com.co.gino.domain.models.Guest
import com.co.gino.domain.repositories.GuestLocalRepository
import com.co.gino.domain.repositories.GuestProxy
import com.co.gino.domain.repositories.GuestRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class GuestsCachedProxy(
    private val localRepository: GuestLocalRepository,
    private val remoteRepository: GuestRemoteRepository
) : GuestProxy {

    override suspend fun getAllGuests(): Flow<List<Guest>> {
       val isEmptyLocalRepository = localRepository.isEmpty()
        return if (isEmptyLocalRepository){
            val response = remoteRepository.getAllGuests()
            response.collect {
                saveGuests(it)
            }
            response
        }
        else localRepository.getAllGuests()
    }

    private fun saveGuests(guests: List<Guest>) = localRepository.insertGuests(guests)
}