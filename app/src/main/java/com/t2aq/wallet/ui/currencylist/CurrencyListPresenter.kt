package com.t2aq.wallet.ui.currencylist

import android.os.AsyncTask
import com.t2aq.wallet.R
import com.t2aq.wallet.WalletApplication
import com.t2aq.wallet.data.database.WalletRoomDatabase
import com.t2aq.wallet.data.dao.CurrencyDao
import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyListPresenter(val currencyListView: CurrencyListContract.View) :
    CurrencyListContract.Presenter {
    override var currencyListDatabase = emptyList<CurrencyModel>()
    override var currencyListServer: List<CurrencyModel>? = null
    private val currencyDao: CurrencyDao =
        WalletRoomDatabase.getDatabase(WalletApplication.instance.applicationContext).currencyDao()


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
                currencyListServer = response.body()
                if (!currencyListServer.isNullOrEmpty()) {
                    currencyListView.setRecyclerData(currencyListServer!!)
                    currencyListView.visibleAddButton()

                    //getCurrencyListFromDatabase()
                }
            }

        })
    }

    override fun getCurrencyListFromDatabase() {
        currencyListDatabase = GetCurrencyListAsyncTask(currencyDao).execute().get()
    }

    override fun insertCurrencyToDatabase(currencyModel: CurrencyModel): Long {
        return InsertCurrencyAsyncTask(currencyDao).execute(currencyModel).get()
    }

    override fun insertCurrencyListToDatabase() {
        for (currency in currencyListServer!!) {
            InsertCurrencyAsyncTask(currencyDao).execute(currency)
        }
        currencyListView.showResult(WalletApplication.instance.resources.getString(R.string.currencylist_itemsadded))
    }

    //Async Tasks___________________________________
    private class InsertCurrencyAsyncTask(val currencyDao: CurrencyDao) :
        AsyncTask<CurrencyModel, Void, Long>() {
        override fun doInBackground(vararg currencyModel: CurrencyModel?): Long {
            return currencyDao.insert(currencyModel[0]!!)
        }
    }

    private class GetCurrencyListAsyncTask(val currencyDao: CurrencyDao) :
        AsyncTask<Void, Void, List<CurrencyModel>>() {
        override fun doInBackground(vararg params: Void?): List<CurrencyModel> {
            return currencyDao.getCurrencyList()
        }

    }


}