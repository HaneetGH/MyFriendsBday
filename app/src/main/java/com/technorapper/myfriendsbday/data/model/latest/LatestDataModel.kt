package com.technorapper.myfriendsbday.data.model.latest

data class LatestDataModel(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Rates,
    val timestamp: Int
)