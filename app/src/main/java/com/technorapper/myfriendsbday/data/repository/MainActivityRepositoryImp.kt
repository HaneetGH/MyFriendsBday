package com.technorapper.myfriendsbday.data.repository

import android.util.Log
import com.technorapper.myfriendsbday.data.db.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityRepositoryImp @Inject constructor(
    private val userInfoDao: UserInfoDao?
) : MainActivityRepository {


    override fun saveDataInDb(name: String, dob: String) {
        var data = UserInfoModel()
        data.name = name
        data.dob = dob
        var jobOne = CoroutineScope(Dispatchers.IO).launch {
            val stepOne = async { userInfoDao?.insert(data) }.await()
        }
        jobOne.invokeOnCompletion { Log.d("res",it.toString()) }
    }

    override fun getAllDataDB(): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.GET))
            // var response: VehicleCategoriesList = null
            try {
                var res = userInfoDao?.getAllUsers()
                emit(DataState.Success(res, Task.GET))
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }
        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it, Task.GET
                )
            )
        }
    }
}