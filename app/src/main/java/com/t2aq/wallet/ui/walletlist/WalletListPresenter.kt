package com.t2aq.wallet.ui.walletlist

import com.t2aq.wallet.data.model.WalletModel
import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.utils.LoginUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WalletListPresenter(val walletListView: WalletListContract.View) : WalletListContract.Presenter {
    override fun getWalletListFromServer() {
        val token = LoginUtils.getTokenFromSharedPreferences() ?: ""
        APIClient.getService()?.walletList(token)?.enqueue(object : Callback<List<WalletModel>> {
            override fun onFailure(call: Call<List<WalletModel>>, t: Throwable) {
                val result = "failed: " + t.message
                walletListView.showResult(result)
            }

            override fun onResponse(call: Call<List<WalletModel>>, response: Response<List<WalletModel>>) {
                val walletList = response.body()
                if (walletList != null && walletList.isNotEmpty())
                    walletListView.setRecyclerData(walletList)
            }
        })
    }
}