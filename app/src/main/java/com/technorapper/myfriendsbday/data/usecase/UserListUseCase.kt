package com.technorapper.myfriendsbday.data.usecase

import android.util.Log
import com.technorapper.myfriendsbday.data.repository.UserListActivityRepository
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListUseCase @Inject constructor(
    private val mainActivityRepository: UserListActivityRepository
)  {

    suspend fun getAllData(): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.GET))
            // var response: VehicleCategoriesList = null
            try {
                mainActivityRepository.getAllDataDB().collect { emit(DataState.Success(it, Task.GET)) }
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
}