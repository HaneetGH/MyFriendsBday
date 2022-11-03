package com.technorapper.myfriendsbday.data.repository

import com.technorapper.myfriendsbday.data.db.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.data.usecase.UserDetailsUseCase
import com.technorapper.myfriendsbday.domain.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val userInfoDao: UserInfoDao
) {

    suspend fun saveDataInDB(name: String, dob: String): Flow<Boolean> {
        return flow {
            var data = UserInfoModel()
            data.name = name
            data.dob = dob
            var jobOne = CoroutineScope(Dispatchers.IO).launch {
                val stepOne = async { userInfoDao.insert(data) }.await()
            }
            jobOne.invokeOnCompletion {  }
            emit(true)
        }
    }}