package com.technorapper.myfriendsbday.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class CurrencyConvertedListModel(
    var from:String,
    var value:Double,
    var to: String,
    var Conversion: Double
)
