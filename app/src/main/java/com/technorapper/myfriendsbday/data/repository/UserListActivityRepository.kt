package com.technorapper.myfriendsbday.data.repository

import com.technorapper.myfriendsbday.domain.DataState
import kotlinx.coroutines.flow.Flow

interface UserListActivityRepository {

    fun getAllDataDB(): Flow<DataState>
}