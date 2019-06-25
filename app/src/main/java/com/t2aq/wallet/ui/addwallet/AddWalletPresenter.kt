package com.t2aq.wallet.ui.addwallet

import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.remote.APIClient
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
                        if (!currency.name.isNullOrEmpty())
                            currencyNameList.add(currency.name)
                    }
                    addWalletView.spinnerSetup(currencyNameList)

                }
            }

        })
    }

}