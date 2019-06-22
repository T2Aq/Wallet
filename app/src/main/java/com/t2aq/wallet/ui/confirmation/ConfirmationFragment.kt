package com.t2aq.wallet.ui.confirmation

import androidx.fragment.app.Fragment

class ConfirmationFragment :Fragment(),ConfirmationContract.View {
    override lateinit var presenter: ConfirmationContract.Presenter

    override fun firstSetup() {
        presenter = ConfirmationPresenter(this)
    }

    override fun initUiListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}