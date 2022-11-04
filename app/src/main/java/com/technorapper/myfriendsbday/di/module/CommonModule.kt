package com.technorapper.myfriendsbday.di.module

import com.technorapper.myfriendsbday.data.repository.MainActivityRepository
import com.technorapper.myfriendsbday.data.repository.MainActivityRepositoryImp
import com.technorapper.myfriendsbday.data.repository.UserListActivityRepository
import com.technorapper.myfriendsbday.data.repository.UserListRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {
    @Binds
    abstract fun bindFoo(foo: MainActivityRepositoryImp): MainActivityRepository
    @Binds
    abstract fun bindUserListRepositoryImp(foo: UserListRepositoryImp): UserListActivityRepository
}