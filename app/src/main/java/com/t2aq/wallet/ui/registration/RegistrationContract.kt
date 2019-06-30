package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface RegistrationContract {

    interface View : BaseView<Presenter> {
        fun showNetworkAvalibility()
        fun showConfirmationPage(phoneNumber:String)
        fun sendPhoneNumber()
    }

    interface Presenter : BasePresenter {
        fun sendPhoneNumber(phoneNumber: String, udid: String)
    }

}