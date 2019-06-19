package com.t2aq.wallet.ui.main

import android.content.Context
import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView

interface MainContract {

    interface View:BaseView<Presenter>{
        fun signedInResult(result: Boolean)
    }

    interface Presenter : BasePresenter{
       fun isUserSignedIn(context: Context)
        fun getToken()
    }

}