package com.technorapper.myfriendsbday.data.repository

import android.util.Log
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListRepositoryImp @Inject constructor(
    private val userInfoDao: UserInfoDao?
) : UserListActivityRepository {

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