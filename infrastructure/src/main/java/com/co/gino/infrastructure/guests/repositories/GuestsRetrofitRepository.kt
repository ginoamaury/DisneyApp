package com.co.gino.infrastructure.guests.repositories

import com.co.gino.domain.exceptions.NoDataGuestException
import com.co.gino.domain.models.Guest
import com.co.gino.domain.repositories.GuestRemoteRepository
import com.co.gino.infrastructure.guests.anticorruption.GuestTranslate
import com.co.gino.infrastructure.httpclient.GuestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GuestsRetrofitRepository(private val guestService: GuestService): GuestRemoteRepository {

    override suspend fun getAllGuests(): Flow<List<Guest>> = flow {
        emit(guestService.getGuests())
    }.catch {
        throw NoDataGuestException()
    }.map { guests ->
        guests.map { GuestTranslate.mapGuestDtoToDomain(it) }
    }

}