package com.technorapper.myfriendsbday.di.module

import android.content.Context
import androidx.room.Room
import com.technorapper.myfriendsbday.data.db.room.Database
import com.technorapper.myfriendsbday.data.usecase.UserDetailsUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, Database::class.java, "test_db"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: Database) =
        db.getUserInfoDaoMaster() // The reason we can implement a Dao for the database


}
