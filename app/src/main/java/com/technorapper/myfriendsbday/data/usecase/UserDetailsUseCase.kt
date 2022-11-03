package com.technorapper.myfriendsbday.data.usecase

import android.util.Log
import com.technorapper.myfriendsbday.data.repository.MainActivityRepository
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(
    private val mainActivityRepository: MainActivityRepository
) {

    suspend fun saveData(name: String, dob: String): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.SAVE))
            // var response: VehicleCategoriesList = null
            try {
                mainActivityRepository.saveDataInDB(name, dob)
                    .collect { emit(DataState.Success(it, Task.SAVE)) }
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }
        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it, Task.SAVE
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }
}