package com.t2aq.wallet.ui.currencylist

import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyListPresenter(val currencyListView: CurrencyListContract.View) :
    CurrencyListContract.Presenter {
    override fun getCurrencyListFromServer() {
        APIClient.getService()?.currencyList()?.enqueue(object : Callback<List<CurrencyModel>> {
            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                val result = "failed: " + t.message
                currencyListView.showResult(result)
            }

            override fun onResponse(call: Call<List<CurrencyModel>>,
                                    response: Response<List<CurrencyModel>>) {
                val result = "responsed: " + response.message()
                currencyListView.showResult(result)
                val currencyList = response.body()
                if (!currencyList.isNullOrEmpty()) currencyListView.setRecyclerData(currencyList)
            }

        })
    }

}