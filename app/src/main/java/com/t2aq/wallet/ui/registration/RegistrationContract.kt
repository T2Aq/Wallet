package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView

interface RegistrationContract {

    interface View : BaseView<Presenter> {
        fun showResult(result: String)
        fun showNetworkAvalibility()
    }

    interface Presenter : BasePresenter {
        fun sendPhoneNumber(phone: String, udid: String)
    }

}