package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface RegistrationContract {

    interface View : BaseView<Presenter> {
        fun showNetworkAvalibility()
        fun sendPhoneNumber(udid:String)
        fun showConfirmationPage(phoneNumber:String)
    }

    interface Presenter : BasePresenter {
        fun sendPhoneNumber(phone: String, udid: String)
    }

}