package com.co.gino.infrastructure.guests.anticorruption

import com.co.gino.domain.models.Guest
import com.co.gino.infrastructure.guests.httpclient.dto.GuestDto
import com.co.gino.infrastructure.guests.persistence.entity.GuestEntity

class GuestTranslate {
    companion object {
        fun mapGuestEntityToDomain(guestEntity: GuestEntity) = Guest(guestEntity.name,guestEntity.hasReservation)
        fun mapGuestDtoToDomain(guestDto: GuestDto) = Guest(guestDto.name,guestDto.hasReservation)
    }
}