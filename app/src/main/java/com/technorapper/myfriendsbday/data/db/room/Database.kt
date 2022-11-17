package com.technorapper.myfriendsbday.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technorapper.myfriendsbday.data.db.room.dao.CurrencyListDao
import com.technorapper.myfriendsbday.data.model.CurrencyListModel

@Database(
    entities = [CurrencyListModel::class], version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {

    abstract fun getCurrencyListDao(): CurrencyListDao
}


