package com.t2aq.wallet.ui.confirmation

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface ConfirmationContract {

    interface View: BaseView<Presenter> {
        val phoneNumber:String
        fun showMainPage()
        fun sendConfirmationCode()
    }

    interface Presenter: BasePresenter {

        fun sendVerificationCode(phone:String,udid:String,deviceName:String,activationCode:Int)
    }
}