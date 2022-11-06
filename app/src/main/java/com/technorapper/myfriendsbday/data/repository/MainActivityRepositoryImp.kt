package com.technorapper.myfriendsbday.data.repository

import android.util.Log
import com.technorapper.myfriendsbday.data.db.room.dao.CurrencyListDao
import com.technorapper.myfriendsbday.data.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import com.technorapper.myfriendsbday.data.remote.RemoteService
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
    private val remoteService: RemoteService, private val dao: CurrencyListDao
) : MainActivityRepository {

    override fun getAllLatestData(): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.GET))
            // var response: VehicleCategoriesList = null
            try {
                var res = remoteService.getLatestData("b4ccc1e0480d4a31a87f90ce3519757a")
                var dataStateValue = DataState.Success(res, Task.GET)
                emit(dataStateValue)
            } catch (e: Exception) {
                Log.e("fetch error", e.message.toString());
            }
        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it, Task.GET
                )
            )
        }
    }

    override fun writeInDB(list: List<CurrencyListModel>) {
        var jobOne = CoroutineScope(Dispatchers.IO).launch {
            val stepOne = async {
                dao.insertAllOrders(list)
            }
        }
    }

    override fun convertCurrency(from: String, value: String): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.CONVERT))
            // var response: VehicleCategoriesList = null
            try {
                // TODO conversion  logic
            } catch (e: Exception) {
                Log.e("fetch error", e.message.toString());
            }
        }.catch {
            /*emit(
                DataState.ErrorThrowable(
                    it, Task.GET
                )
            )*/
        }
    }
}