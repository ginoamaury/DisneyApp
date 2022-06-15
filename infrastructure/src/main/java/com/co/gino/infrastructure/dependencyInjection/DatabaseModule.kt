package com.co.gino.infrastructure.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.co.gino.domain.models.Guest
import com.co.gino.infrastructure.guests.persistence.dao.GuestDao
import com.co.gino.infrastructure.persintence.GuestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): GuestDatabase = Room.databaseBuilder(
        app,
        GuestDatabase::class.java,
        "guest-db"
    ).build()

    @Provides
    fun providesGuestDao (database: GuestDatabase) : GuestDao = database.guestDao()

}