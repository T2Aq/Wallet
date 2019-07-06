package com.t2aq.wallet.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.t2aq.wallet.data.dao.CurrencyDao
import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.utils.DbConstants

@Database(entities = [CurrencyModel::class], version = 1)
abstract class WalletRoomDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: WalletRoomDatabase? = null

        fun getDatabase(context: Context): WalletRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletRoomDatabase::class.java,
                    DbConstants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}