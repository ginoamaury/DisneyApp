package com.co.gino.domain.repositories

import com.co.gino.domain.models.Guest
import kotlinx.coroutines.flow.Flow

interface GuestProxy {
    suspend fun getAllGuests(): Flow<List<Guest>>
}