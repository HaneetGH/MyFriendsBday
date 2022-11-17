package com.technorapper.myfriendsbday.data.repository

import android.util.Log
import com.technorapper.myfriendsbday.common.Utils
import com.technorapper.myfriendsbday.data.MyPreference
import com.technorapper.myfriendsbday.data.db.room.dao.CurrencyListDao
import com.technorapper.myfriendsbday.data.model.CurrencyConvertedListModel
import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import com.technorapper.myfriendsbday.data.model.latest.LatestDataModel
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
import java.util.*
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberProperties

class MainActivityRepositoryImp @Inject constructor(
    private val remoteService: RemoteService,
    private val dao: CurrencyListDao,
    private val myPreference: MyPreference
) : MainActivityRepository {

    override fun getAllLatestData(): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.GET))
            try {
                if (!Utils.isSyncUnable(myPreference.getSync())) {
                    myPreference.setSync(Calendar.getInstance().timeInMillis)
                    var result = remoteService.getLatestData("1a228103229f4394af46c805c5503d83")
                    var dataStateValue = DataState.Success(result, Task.GET)
                    getValuesFromData(dataStateValue).collect { emit(it) }
                } else {
                    dao.getAllCurrencyList().collect { it ->
                        if (it.isEmpty()) {
                            myPreference.setSync(Calendar.getInstance().timeInMillis)
                            var res = remoteService.getLatestData("1a228103229f4394af46c805c5503d83")
                            var dataStateValue = DataState.Success(res, Task.GET)
                            getValuesFromData(dataStateValue).collect { emit(it) }
                        } else {
                            emit(DataState.Success(it, Task.GET))
                        }
                    }
                }
            } catch (e: Exception) {
                dao.getAllCurrencyList().collect {
                    if (it.isEmpty()) {
                        emit(DataState.ErrorThrowable(e, Task.GET))
                    } else {
                        emit(DataState.Success(it, Task.GET))
                    }
                }
            }
        }.catch { error ->
            dao.getAllCurrencyList().collect {
                if (it.isEmpty()) {
                    emit(DataState.ErrorThrowable(error, Task.GET))
                } else {
                    emit(DataState.Success(it, Task.GET))
                }
            }
        }
    }

    private suspend fun getValuesFromData(it: DataState): Flow<DataState> {
        var result = it
        var listOfCurrency = mutableListOf<CurrencyListModel>()
        var data: DataState = it as DataState
        when (it) {
            is DataState.Success<*> -> {
                var dataModel = (data as DataState.Success<*>).data as LatestDataModel

                dataModel.rates.javaClass.kotlin.declaredMemberProperties.forEach {
                    with(it) {
                        println("$returnType: $name = ${get(dataModel.rates)}")
                        var localData = CurrencyListModel()
                        localData.currency = name;
                        localData.rate = get(dataModel.rates) as Float
                        listOfCurrency.add(localData)
                    }
                }
                writeInDB(listOfCurrency)
                result = DataState.Success(listOfCurrency, Task.GET)
            }
        }
        return flow { emit(result) }
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


                // Convert from into USD by value/from_rate
                // result from above multiply by from currency rate
                var baseCurrency = dao.getCurrency(from)
                var localUSDValue = value / baseCurrency.rate

                dao.getAllCurrencyList().collect {
                    it.forEach {
                        var resultValue = localUSDValue * it.rate
                        var convertionOBj = CurrencyConvertedListModel(
                            from, value, it.currency, resultValue
                        )
                        Log.d(
                            "Conversion",
                            convertionOBj.value.toString() + "--" + convertionOBj.Conversion
                        )

                        listOfConversion.add(convertionOBj)
                    }
                    emit(DataState.Success(listOfConversion, Task.CONVERT))

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