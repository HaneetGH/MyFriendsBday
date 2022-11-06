package com.technorapper.myfriendsbday.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencyListMaster" + "")
data class CurrencyListModel(
    @PrimaryKey @ColumnInfo(name = "currency") var currency: String,
    @ColumnInfo(name = "rate") var rate: Float
) {
    constructor() : this(
        "", 0.0F
    )

}
