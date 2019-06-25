package com.t2aq.wallet.ui.main

import android.content.Context
import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface MainContract {

    interface View: BaseView<Presenter> {
        fun setToken(token: String?)

    }

    interface Presenter : BasePresenter {
       fun isUserSignedIn(context: Context)
    }

}