package com.t2aq.wallet.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.t2aq.wallet.utils.DbConstants


class WalletSqliteDatabase(context: Context) :
    SQLiteOpenHelper(context, DbConstants.SQL_DATABASE_NAME, null, DbConstants.SQL_DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        val SQL_CREATE_WALLETS_TABLE = ("CREATE TABLE " + DbConstants.TABLE_WALLET + " ("
                + DbConstants.ID_WALLET_MODEL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConstants.NAME_WALLET_MODEL + " TEXT NOT NULL, "
                + DbConstants.CURRENCY_CODE_ALL + " TEXT NOT NULL) ")

        // Execute the SQL statement
        db?.execSQL(SQL_CREATE_WALLETS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}