package com.technorapper.myfriendsbday.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technorapper.myfriendsbday.data.db.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao

@Database(
    entities = [UserInfoModel::class], version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {

    abstract fun getUserInfoDaoMaster(): UserInfoDao
}


