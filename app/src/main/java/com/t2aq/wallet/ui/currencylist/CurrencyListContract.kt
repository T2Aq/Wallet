package com.t2aq.wallet.ui.currencylist

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView
import com.t2aq.wallet.data.model.CurrencyModel

interface CurrencyListContract {

    interface View:BaseView<Presenter>{

        fun setRecyclerData(currencyList:List<CurrencyModel>)
        fun visibleAddButton()
    }

    interface Presenter:BasePresenter{
        var currencyListDatabase: List<CurrencyModel>
        fun getCurrencyListFromServer()
        fun getCurrencyListFromDatabase()
        fun insertCurrencyToDatabase(currencyModel: CurrencyModel):Long
        fun insertCurrencyListToDatabase()
        var currencyListServer: List<CurrencyModel>?
    }
}