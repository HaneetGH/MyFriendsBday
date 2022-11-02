package com.technorapper.myfriendsbday

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technorapper.myfriendsbday.data.repository.MainActivityRepository
import com.technorapper.myfriendsbday.data.usecase.UserDetailsUseCase
import com.technorapper.myfriendsbday.domain.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val usecase: UserDetailsUseCase
) : ViewModel() {
    private val _uiState: MutableLiveData<DataState> = MutableLiveData()
    val uiState: MutableLiveData<DataState> get() = _uiState
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.SaveData -> {
                    usecase.saveData(
                        mainStateEvent.name, mainStateEvent.dob
                    ).collect { uiState.value = it }
                }

            }

        }
    }
}

sealed class MainStateEvent {

    data class SaveData(var name: String, var dob: String) : MainStateEvent()
    object None : MainStateEvent()
}