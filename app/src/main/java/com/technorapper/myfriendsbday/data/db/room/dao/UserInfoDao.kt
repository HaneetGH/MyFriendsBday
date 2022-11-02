package com.technorapper.myfriendsbday.data.db.room.dao

import androidx.room.*
import com.technorapper.myfriendsbday.data.db.model.UserInfoModel

@Dao
interface UserInfoDao {

    //Adds a contact to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(LocationTable: UserInfoModel?): Long

    // Removes a contact from the database
    @Delete
    fun delete(person: UserInfoModel?)

    // Gets all contact in the database
    @Query("select * from userDetailsMaster Order by id DESC")
    fun getAllUsers(): List<UserInfoModel>


    @Update
    fun updateUser(LocationTable: UserInfoModel?)

    @Update
    fun updateUser(vararg LocationTable: UserInfoModel?)


    @Query("delete from userDetailsMaster")
    fun deleteAll()

    @Query("DELETE FROM userDetailsMaster")
    fun nukeLocation()


}