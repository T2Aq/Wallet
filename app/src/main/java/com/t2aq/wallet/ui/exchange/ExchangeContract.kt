package com.t2aq.wallet.ui.exchange

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface ExchangeContract {
    interface View : BaseView<Presenter> {

        fun spinnerSetup(currencyNameList: List<String>)
        fun setText(result:Float,flag:String)
    }

    interface Presenter : BasePresenter {

        fun getRateListFromServer()
        fun calculateFromSource(input: Float, currencyCode: String)
        fun calculateFromDestination(input: Float, currencyCode: String)

    }
}