package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView

interface RegistrationContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
        fun login(phoneNumber:String,  uid:String)
        fun sendPhoneNumber(phoneNumber:String)
    }



}