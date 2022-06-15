package com.co.gino.domain.repositories

import com.co.gino.domain.models.Guest
import kotlinx.coroutines.flow.Flow

interface GuestLocalRepository {
    fun isEmpty():Boolean
    fun getAllGuests(): Flow<List<Guest>>
    fun insertGuests(guests: List<Guest>)
}