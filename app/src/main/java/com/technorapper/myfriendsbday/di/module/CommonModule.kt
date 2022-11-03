package com.technorapper.myfriendsbday.di.module

import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.data.repository.MainActivityRepository
import com.technorapper.myfriendsbday.data.repository.MainActivityRepositoryImp
import com.technorapper.myfriendsbday.data.usecase.UserDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {
    @Binds
    abstract fun bindFoo(foo: MainActivityRepositoryImp): MainActivityRepository
}