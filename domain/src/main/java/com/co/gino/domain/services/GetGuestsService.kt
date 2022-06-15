package com.co.gino.domain.services

import com.co.gino.domain.repositories.GuestProxy

class GetGuestsService(private val guestProxy: GuestProxy) {
    suspend operator fun invoke() = guestProxy.getAllGuests()
}