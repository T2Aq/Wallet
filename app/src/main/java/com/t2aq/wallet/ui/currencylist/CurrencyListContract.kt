package com.t2aq.wallet.ui.currencylist

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView
import com.t2aq.wallet.data.model.CurrencyModel

interface CurrencyListContract {

    interface View:BaseView<Presenter>{

        fun setRecyclerData(currencyList:List<CurrencyModel>)
    }

    interface Presenter:BasePresenter{

        fun getCurrencyListFromServer()
    }
}