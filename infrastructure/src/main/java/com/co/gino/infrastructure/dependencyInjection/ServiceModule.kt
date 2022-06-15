package com.co.gino.infrastructure.dependencyInjection

import com.co.gino.domain.repositories.GuestProxy
import com.co.gino.domain.services.GetGuestsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    fun getGuests (guestProxy: GuestProxy) : GetGuestsService = GetGuestsService(guestProxy)
}