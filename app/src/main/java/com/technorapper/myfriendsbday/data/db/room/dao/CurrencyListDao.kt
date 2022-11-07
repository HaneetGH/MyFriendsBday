package com.technorapper.myfriendsbday.data.db.room.dao

import androidx.room.*
import com.technorapper.myfriendsbday.data.model.CurrencyListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyListDao {

    //Adds a contact to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: CurrencyListModel?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrders(order: List<CurrencyListModel?>?)

    // Removes a contact from the database
    @Delete
    fun delete(currency: CurrencyListModel?)

    // Gets all contact in the database
    @Query("select * from currencyListMaster")
    fun getAllCurrencyList(): Flow<List<CurrencyListModel>>

    @Query("select * from currencyListMaster where currency = :value")
    suspend fun getCurrency(value: String): CurrencyListModel

    @Update
    fun updateUser(currencyList: CurrencyListModel?)

    @Update
    fun updateUser(vararg currencyList: CurrencyListModel?)


    @Query("delete from currencyListMaster")
    fun deleteAll()

    @Query("DELETE FROM currencyListMaster")
    fun nukeLocation()


}
