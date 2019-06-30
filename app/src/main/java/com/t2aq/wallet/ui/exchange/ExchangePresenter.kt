package com.t2aq.wallet.ui.exchange

import com.t2aq.wallet.data.model.RateModel
import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.utils.Constants
import com.t2aq.wallet.utils.DbConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangePresenter(val exchangeView: ExchangeContract.View) : ExchangeContract.Presenter {

    var currencyRateMap = HashMap<String, Float>()
    override fun getRateListFromServer() {
        APIClient.getService()?.rateList()?.enqueue(object : Callback<List<RateModel>> {
            override fun onFailure(call: Call<List<RateModel>>, t: Throwable) {
                val result = "failed: " + t.message
                exchangeView.showResult(result)
            }

            override fun onResponse(
                    call: Call<List<RateModel>>,
                    response: Response<List<RateModel>>
            ) {
                val result = "responsed: " + response.message()
                exchangeView.showResult(result)
                val rateListServer = response.body()
                if (!rateListServer.isNullOrEmpty()) {
                    val rateCodeList = ArrayList<String>()
                    for (rate in rateListServer) {
                        rateCodeList.add(rate.currencyCode)
                        currencyRateMap[rate.currencyCode] = rate.buy
                    }
                    exchangeView.spinnerSetup(rateCodeList)
                }
            }

        })
    }

    override fun calculateFromSource(input: Float, currencyCode: String) {
        val rate = currencyRateMap[currencyCode] ?: 1f
        val result = (input / Constants.RIAL_DOLLAR_RATE) * rate
        exchangeView.setText(result, Constants.DESTINATION)
    }

    override fun calculateFromDestination(input: Float, currencyCode: String) {
        val rate = currencyRateMap[currencyCode] ?: 1f
        val result = (input * Constants.RIAL_DOLLAR_RATE) / rate
        exchangeView.setText(result, Constants.SOURCE)
    }

}