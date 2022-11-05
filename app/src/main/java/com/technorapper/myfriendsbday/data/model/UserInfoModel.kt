package com.technorapper.myfriendsbday.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetailsMaster" + "")
data class UserInfoModel(
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "name") var name: String,

    @ColumnInfo(name = "dob") var dob: String,
) {
    constructor() : this(
        0, "", ""
    )
}
