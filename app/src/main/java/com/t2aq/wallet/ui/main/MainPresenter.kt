package com.t2aq.wallet.ui.main

import android.content.Context
import android.widget.Toast
import com.t2aq.wallet.utils.LoginUtils
import com.t2aq.wallet.utils.NetworkUtils

class MainPresenter(val mainView:MainContract.View) : MainContract.Presenter {

    override fun isUserSignedIn(context: Context) {
            val result = LoginUtils.isLogin()
            mainView.signedInResult(result)
    }

    override fun getToken() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}