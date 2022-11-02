package com.technorapper.myfriendsbday.data.repository

import com.technorapper.myfriendsbday.data.db.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.data.usecase.UserDetailsUseCase
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val userInfoDao: UserInfoDao
) {

    suspend fun saveDataInDB(name: String, dob: String) {
        var data = UserInfoModel()
        data.name = name
        data.dob = dob
        userInfoDao.insert(data)
    }
}