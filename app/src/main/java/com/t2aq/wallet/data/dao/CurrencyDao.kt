package com.t2aq.wallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.utils.DbConstants

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM ${DbConstants.TABLE_CURRENCY} ORDER BY ${DbConstants.NAME_CURRENCY_MODEL} ASC")
    fun getCurrencyList(): List<CurrencyModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyModel: CurrencyModel): Long
}