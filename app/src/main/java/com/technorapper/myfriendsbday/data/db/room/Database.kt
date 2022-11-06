package com.technorapper.myfriendsbday.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technorapper.myfriendsbday.data.db.room.dao.CurrencyListDao
import com.technorapper.myfriendsbday.data.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.data.model.CurrencyListModel

@Database(
    entities = [UserInfoModel::class, CurrencyListModel::class], version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {

    abstract fun getUserInfoDaoMaster(): UserInfoDao
    abstract fun getCurrencyListDao(): CurrencyListDao
}


