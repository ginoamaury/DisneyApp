package com.co.gino.infrastructure.guests.persistence.dao

import androidx.room.*
import com.co.gino.infrastructure.guests.persistence.entity.GuestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDao {
    @Transaction
    @Query("SELECT * FROM GuestEntity")
    fun getGuests(): Flow<List<GuestEntity>>

    @Transaction
    @Query("SELECT count(*) FROM GuestEntity")
    fun getCountGuests(): Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGuests(guests: List<GuestEntity>)
}