package com.t2aq.wallet.ui.addwallet

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface AddWalletContract {
    interface View:BaseView<Presenter>{
        fun spinnerSetup(currencyNameList:List<String>)
    }

    interface Presenter:BasePresenter{
        fun getCurrencyListFromServer()
    }
}