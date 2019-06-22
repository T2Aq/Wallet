package com.t2aq.wallet.ui.confirmation

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.textinputlayout_confirmation_validationcode

class ConfirmationFragment :Fragment(),ConfirmationContract.View {
    override lateinit var presenter: ConfirmationContract.Presenter

    override fun firstSetup() {
        presenter = ConfirmationPresenter(this)
    }

    override fun initUiListeners() {
        button_registration_register.setOnClickListener {
            presenter.sendVerificationCode(textinputedittext_confirmation_validationcode.text.toString())

        }
    }

}