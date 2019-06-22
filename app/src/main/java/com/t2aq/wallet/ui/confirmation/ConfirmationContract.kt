package com.t2aq.wallet.ui.confirmation

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView

interface ConfirmationContract {

    interface View:BaseView<Presenter>{

    }

    interface Presenter:BasePresenter{

        fun sendVerificationCode(code:String)
    }
}