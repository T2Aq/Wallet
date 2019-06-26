package com.t2aq.wallet.ui.addwallet

import android.content.Context
import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface AddWalletContract {
    interface View:BaseView<Presenter>{
        fun spinnerSetup(currencyNameList:List<String>)
        fun finishAddWalletActivity()
    }

    interface Presenter:BasePresenter{
        fun getCurrencyListFromServer()
        fun insertWallet(context: Context,currencyCode: String,walletName: String)
        fun addWalletToDatabase(context: Context, currencyCode: String, walletName: String)
    }
}