package com.co.gino.infrastructure.dependencyInjection

import com.co.gino.domain.repositories.GuestLocalRepository
import com.co.gino.domain.repositories.GuestProxy
import com.co.gino.domain.repositories.GuestRemoteRepository
import com.co.gino.infrastructure.guests.persistence.dao.GuestDao
import com.co.gino.infrastructure.guests.repositories.GuestsCachedProxy
import com.co.gino.infrastructure.guests.repositories.GuestsRetrofitRepository
import com.co.gino.infrastructure.guests.repositories.GuestsRoomRepository
import com.co.gino.infrastructure.httpclient.GuestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesRepository(
        localRepository: GuestLocalRepository,
        remoteRepository: GuestRemoteRepository
    ): GuestProxy =
        GuestsCachedProxy(localRepository, remoteRepository)

    @Provides
    fun providesLocalSource(guestDao: GuestDao): GuestLocalRepository =
        GuestsRoomRepository(guestDao)

    @Provides
    fun providesRemoteSource(guestService: GuestService): GuestRemoteRepository =
        GuestsRetrofitRepository(guestService)

}