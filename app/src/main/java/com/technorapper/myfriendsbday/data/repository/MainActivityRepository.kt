package com.technorapper.myfriendsbday.data.repository

import com.technorapper.myfriendsbday.domain.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {

    fun saveDataInDb(name: String, dob: String)
    fun getAllDataDB(): Flow<DataState>
}