package com.technorapper.myfriendsbday

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import com.technorapper.myfriendsbday.data.model.UserInfoModel


class DateStateForLatestData(date: List<CurrencyListModel>) {
    var dateStateChangeList by mutableStateOf(date)
}
