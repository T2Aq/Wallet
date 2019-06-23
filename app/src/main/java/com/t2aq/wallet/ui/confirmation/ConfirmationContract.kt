package com.t2aq.wallet.ui.confirmation

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView

interface ConfirmationContract {

    interface View:BaseView<Presenter>{
        val phoneNumber:String
        fun startMainPage()
    }

    interface Presenter:BasePresenter{

        fun sendVerificationCode(phone:String,udid:String,deviceName:String,activationCode:Int)
    }
}