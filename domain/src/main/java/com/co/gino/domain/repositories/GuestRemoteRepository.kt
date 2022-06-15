package com.co.gino.domain.repositories

import com.co.gino.domain.models.Guest
import kotlinx.coroutines.flow.Flow

interface GuestRemoteRepository {
    suspend fun getAllGuests(): Flow<List<Guest>>
}