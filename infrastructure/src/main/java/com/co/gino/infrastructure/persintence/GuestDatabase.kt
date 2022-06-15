package com.co.gino.infrastructure.persintence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.co.gino.infrastructure.guests.persistence.dao.GuestDao
import com.co.gino.infrastructure.guests.persistence.entity.GuestEntity

@Database(entities = [GuestEntity::class],version = 1, exportSchema = false)
abstract class GuestDatabase:RoomDatabase() {
    abstract fun guestDao(): GuestDao
}