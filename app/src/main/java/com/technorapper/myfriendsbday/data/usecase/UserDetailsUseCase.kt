package com.technorapper.myfriendsbday.data.usecase

import android.util.Log
import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import com.technorapper.myfriendsbday.data.repository.MainActivityRepository
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(
    private val mainActivityRepository: MainActivityRepository
) {

    suspend fun getAllLatestData(): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.GET))
            // var response: VehicleCategoriesList = null
            try {
                mainActivityRepository.getAllLatestData().collect {
                    emit(it)
                }
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }
        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it, Task.GET
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }

    suspend fun convertCurrency(from: String, value: Double): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.CONVERT))
            // var response: VehicleCategoriesList = null
            try {
                mainActivityRepository.getAllLatestData().collect {
                    emit(it)
                }
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }
        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it, Task.GET
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }

    fun writeInDB(list: List<CurrencyListModel>) {
        mainActivityRepository.writeInDB(list)
    }
}