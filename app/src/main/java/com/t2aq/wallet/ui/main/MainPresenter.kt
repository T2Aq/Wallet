package com.t2aq.wallet.ui.main

import android.content.Context
import com.t2aq.wallet.utils.LoginUtils

class MainPresenter(val mainView: MainContract.View) : MainContract.Presenter {

    override fun isUserSignedIn(context: Context) {
        val result = LoginUtils.hasToken()
        mainView.signedInResult(result)
    }

    override fun getToken() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}