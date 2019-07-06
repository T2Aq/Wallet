package com.t2aq.wallet.ui.addwallet

import android.content.ContentValues
import android.content.Context
import android.os.Handler
import com.t2aq.wallet.R
import com.t2aq.wallet.data.database.WalletSqliteDatabase
import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.model.WalletModel
import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.utils.DbConstants
import com.t2aq.wallet.utils.LoginUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddWalletPresenter(val addWalletView: AddWalletContract.View) : AddWalletContract.Presenter {

    override fun getCurrencyListFromServer() {
        APIClient.getService()?.currencyList()?.enqueue(object : Callback<List<CurrencyModel>> {
            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                val result = "failed: " + t.message
                addWalletView.showResult(result, false)
            }

            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                val result = "responsed: " + response.message()
                val currencyListServer = response.body()
                if (!currencyListServer.isNullOrEmpty()) {
                    val currencyNameList = ArrayList<String>()
                    for (currency in currencyListServer) {
                        if (!currency.code.isNullOrEmpty())
                            currencyNameList.add(currency.code)
                    }
                    addWalletView.spinnerSetup(currencyNameList)
                    addWalletView.showResult(result, true)
                } else
                    addWalletView.showResult(result, false)


            }

        })
    }

    override fun insertWallet(context: Context, currencyCode: String, walletName: String) {
        val token = LoginUtils.getTokenFromSharedPreferences() ?: ""
        APIClient.getService()?.insertWallet(token, walletName, currencyCode)?.enqueue(object : Callback<WalletModel> {
            override fun onFailure(call: Call<WalletModel>, t: Throwable) {
                val result = "failed: " + t.message
                addWalletView.showResult(result, true)
            }

            override fun onResponse(call: Call<WalletModel>, response: Response<WalletModel>) {
                val result = "responsed: " + response.message()

                if (response.code() == 200) {
                    addWalletView.showResult(result, false)
                    addWalletToDatabase(context, currencyCode, walletName)

                } else
                    addWalletView.showResult(result, true)

            }

        })
    }

    override fun addWalletToDatabase(context: Context, currencyCode: String, walletName: String) {
        val values = ContentValues()
        values.put(DbConstants.NAME_WALLET_MODEL, walletName)
        values.put(DbConstants.CURRENCY_CODE_ALL, currencyCode)

        val walletSqlDatabase = WalletSqliteDatabase(context)
        val database = walletSqlDatabase.writableDatabase
        val id = database?.insert(DbConstants.TABLE_WALLET, null, values) ?: 0
        if (id > 0) {
            Handler().postDelayed(
                {
                    addWalletView.showResult(
                        context.resources.getString(R.string.addwallet_itemaddedtolocaldatabase),
                        false
                    )
                },
                1500
            )
            addWalletView.finishAddWalletActivity()
        } else
            addWalletView.showResult(
                context.resources.getString(R.string.addwallet_itemnotaddedtolocaldatabase),
                true
            )

    }
}