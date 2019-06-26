package com.t2aq.wallet.ui.exchange

import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface ExchangeContract {
    interface View:BaseView<Presenter>{

        fun spinnerSetup(currencyNameList: List<String>)
    }

    interface Presenter:BasePresenter{

        fun getCurrencyListFromServer()
        var currencyListServer: List<CurrencyModel>?
    }
}