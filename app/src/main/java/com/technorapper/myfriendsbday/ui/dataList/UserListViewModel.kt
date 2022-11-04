package com.technorapper.myfriendsbday.ui.dataList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technorapper.myfriendsbday.data.usecase.UserListUseCase
import com.technorapper.myfriendsbday.domain.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usecase: UserListUseCase
) : ViewModel() {
    private val _uiState: MutableLiveData<DataState> = MutableLiveData()
    val uiState: MutableLiveData<DataState> get() = _uiState


    @JvmName("getUiState1")
    fun getUiState(): MutableLiveData<DataState> {
        return uiState
    }

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.getAllData -> {
                    usecase.getAllData().collect {
                        uiState.value = it
                    }
                }

            }

        }
    }
}

sealed class MainStateEvent {

    data class SaveData(var name: String, var dob: String) : MainStateEvent()
    object getAllData : MainStateEvent()
    object None : MainStateEvent()
}