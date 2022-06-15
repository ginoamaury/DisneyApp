package com.co.gino.infrastructure.guests.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.co.gino.domain.models.Guest

@Entity
data class GuestEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name: String,
    val hasReservation: Boolean
){
    constructor(guest:Guest): this(
        name = guest.name,
        hasReservation = guest.hasReservation
    )
}