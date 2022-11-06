package com.technorapper.myfriendsbday.data.repository

import android.util.Log
import com.technorapper.myfriendsbday.data.db.room.dao.CurrencyListDao
import com.technorapper.myfriendsbday.data.model.UserInfoModel
import com.technorapper.myfriendsbday.data.db.room.dao.UserInfoDao
import com.technorapper.myfriendsbday.data.model.CurrencyConvertedListModel
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

    override fun convertCurrency(from: String, value: Double): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.CONVERT))
            // var response: VehicleCategoriesList = null
            try {
                var listOfConversion = mutableListOf<CurrencyConvertedListModel>()

                var jobOne = CoroutineScope(Dispatchers.IO).launch {
                    val stepOne = async {
                        // Convert from into USD by value/from_rate
                        // result from above multiply by from currency rate
                        var baseCurrency = dao.getCurrency(from)
                        var localUSDValue = value / baseCurrency.rate
                        dao.getAllCurrencyList().forEach {
                            var resultValue = localUSDValue * it.rate
                            var convertionOBj =
                                CurrencyConvertedListModel(from, value, it.currency, resultValue)
                            Log.d("Conversion", convertionOBj.value.toString() + "--"+convertionOBj.Conversion)

                            listOfConversion.add(convertionOBj)
                        }
                        emit(DataState.Success(listOfConversion, Task.CONVERT))
                    }.await()
                }
            } catch (e: Exception) {
                Log.e("fetch error", e.message.toString());
            }
        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it, Task.CONVERT
                )
            )
        }
    }
}