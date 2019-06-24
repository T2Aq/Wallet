package com.t2aq.wallet.data

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.t2aq.wallet.data.dao.CurrencyDao
import com.t2aq.wallet.data.model.CurrencyModel

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