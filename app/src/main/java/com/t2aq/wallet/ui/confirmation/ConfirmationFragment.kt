package com.t2aq.wallet.ui.confirmation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.mainpage.MainPageActivity
import com.t2aq.wallet.utils.Constants
import com.t2aq.wallet.utils.LoginUtils
import kotlinx.android.synthetic.main.fragment_confirmation.*

class ConfirmationFragment : Fragment(), ConfirmationContract.View {
    override lateinit var presenter: ConfirmationContract.Presenter
    override lateinit var phoneNumber: String


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()

    }

    override fun firstSetup() {
        presenter = ConfirmationPresenter(this)
        phoneNumber = arguments!!.getString(Constants.PHONE_NUMBER) ?: ""
        Log.v("show", "in confirmation fragment")
    }

    override fun initUiListeners() {
        button_confirmation_confirm.setOnClickListener {
            val phoneInputText = textinputedittext_confirmation_validationcode.text
            if (!phoneInputText.isNullOrEmpty()) {
                val udid = LoginUtils.getPhoneUdid(context!!)
                val deviceName = android.os.Build.MODEL
                val activationCode =
                    textinputedittext_confirmation_validationcode.text!!.trim().toString().toInt()
                presenter.sendVerificationCode(phoneNumber, udid, deviceName, activationCode)
            } else {
                showResult(resources.getString(R.string.confirmation_noverificationcode))
            }
        }
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_confirmation_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun showMainPage() {
        val intent = Intent(context, MainPageActivity::class.java)
        startActivity(intent)
        activity?.finish()

    }



}