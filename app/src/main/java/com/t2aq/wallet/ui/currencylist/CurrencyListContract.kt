package com.t2aq.wallet.ui.currencylist

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView
import com.t2aq.wallet.data.model.CurrencyModel

interface CurrencyListContract {

    interface View: BaseView<Presenter> {

        fun setRecyclerData(currencyList:List<CurrencyModel>)
        fun visibleAddButton()
    }

    interface Presenter: BasePresenter {
        var currencyListDatabase: List<CurrencyModel>
        var currencyListServer: List<CurrencyModel>?
        fun getCurrencyListFromServer()
        fun getCurrencyListFromDatabase()
        fun insertCurrencyToDatabase(currencyModel: CurrencyModel):Long
        fun insertCurrencyListToDatabase()

    }
}