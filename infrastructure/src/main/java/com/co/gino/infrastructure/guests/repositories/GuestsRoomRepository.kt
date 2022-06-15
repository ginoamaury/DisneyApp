package com.co.gino.infrastructure.guests.repositories

import com.co.gino.domain.models.Guest
import com.co.gino.domain.repositories.GuestLocalRepository
import com.co.gino.infrastructure.guests.anticorruption.GuestTranslate
import com.co.gino.infrastructure.guests.persistence.dao.GuestDao
import com.co.gino.infrastructure.guests.persistence.entity.GuestEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GuestsRoomRepository (private val guestDao: GuestDao): GuestLocalRepository{

    override fun isEmpty(): Boolean = guestDao.getCountGuests() <= 0

    override fun getAllGuests(): Flow<List<Guest>> = guestDao.getGuests().map { guests -> guests.map { GuestTranslate.mapGuestEntityToDomain(it) }}

    override fun insertGuests(guests: List<Guest>)  = guestDao.insertGuests(guests = guests.map { GuestEntity(it) })

}