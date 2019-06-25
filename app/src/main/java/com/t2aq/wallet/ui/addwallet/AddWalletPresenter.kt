package com.t2aq.wallet.ui.addwallet

import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.model.WalletModel
import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.utils.LoginUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddWalletPresenter(val addWalletView: AddWalletContract.View) : AddWalletContract.Presenter {

    override fun getCurrencyListFromServer() {
        APIClient.getService()?.currencyList()?.enqueue(object : Callback<List<CurrencyModel>> {
            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                val result = "failed: " + t.message
                addWalletView.showResult(result)
            }

            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                val result = "responsed: " + response.message()
                addWalletView.showResult(result)
                val currencyListServer = response.body()
                if (!currencyListServer.isNullOrEmpty()) {
                    val currencyNameList = ArrayList<String>()
                    for (currency in currencyListServer) {
                        if (!currency.code.isNullOrEmpty())
                            currencyNameList.add(currency.code)
                    }
                    addWalletView.spinnerSetup(currencyNameList)

                }
            }

        })
    }

    override fun insertWallet(currencyCode: String, walletName: String) {
        val token = LoginUtils.getTokenFromSharedPreferences() ?: ""
        APIClient.getService()?.insertWallet(token, walletName, currencyCode)?.enqueue(object : Callback<WalletModel> {
            override fun onFailure(call: Call<WalletModel>, t: Throwable) {
                val result = "failed: " + t.message
                addWalletView.showResult(result)
            }

            override fun onResponse(call: Call<WalletModel>, response: Response<WalletModel>) {
                val result = "responsed: " + response.message()
                addWalletView.showResult(result)
                addWalletView.finishAddWalletActivity()
                //TODO add wallet in database
                //getWalletList()

            }

        })
    }

    override fun getWalletList() {
        val token = LoginUtils.getTokenFromSharedPreferences() ?: ""
        APIClient.getService()?.walletList(token)?.enqueue(object : Callback<List<WalletModel>> {
            override fun onFailure(call: Call<List<WalletModel>>, t: Throwable) {
                val result = "failed: " + t.message
                addWalletView.showResult(result)
            }

            override fun onResponse(call: Call<List<WalletModel>>, response: Response<List<WalletModel>>) {
                val result = "responsed: " + response.message()+", " + response.body()?.size + "row exists"
                addWalletView.showResult(result)
            }
        })

    }
}