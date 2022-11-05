package com.technorapper.myfriendsbday.data.remote

import com.technorapper.myfriendsbday.data.model.latest.LatestDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("latest.json")
    suspend fun getLatestData(
        @Query("app_id") appID: String,
    ): LatestDataModel

}