package com.technorapper.myfriendsbday.data.repository

import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import com.technorapper.myfriendsbday.data.model.latest.LatestDataModel
import com.technorapper.myfriendsbday.domain.DataState
import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {
    fun getAllLatestData(): Flow<DataState>
    fun writeInDB(list:List<CurrencyListModel>)
    fun convertCurrency(from: String, value: Double): Flow<DataState>
}