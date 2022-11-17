package com.technorapper.myfriendsbday

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import com.technorapper.myfriendsbday.data.model.latest.LatestDataModel
import com.technorapper.myfriendsbday.data.usecase.UserDetailsUseCase
import com.technorapper.myfriendsbday.domain.DataState
import com.technorapper.myfriendsbday.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberProperties

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val usecase: UserDetailsUseCase
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
                    usecase.getAllLatestData().collect {
                        uiState.value = it
                    }
                }
                is MainStateEvent.convertCurrency -> {
                    usecase.convertCurrency(mainStateEvent.from, mainStateEvent.value).collect {
                        uiState.value = it
                    }
                }

            }

        }
    }



}

sealed class MainStateEvent {

    object getAllData : MainStateEvent()
    data class convertCurrency(var from: String, var value: Double) : MainStateEvent()
    object None : MainStateEvent()
}