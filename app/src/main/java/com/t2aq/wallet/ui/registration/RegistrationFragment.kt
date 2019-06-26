package com.t2aq.wallet.ui.registration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.confirmation.ConfirmationActivity
import com.t2aq.wallet.utils.Constants
import com.t2aq.wallet.utils.LoginUtils
import com.t2aq.wallet.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(), RegistrationContract.View {


    override lateinit var presenter: RegistrationContract.Presenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()
    }


    override fun firstSetup() {
        presenter = RegistrationPresenter(this)
        showNetworkAvalibility()
    }

    override fun initUiListeners() {

        button_registration_register.setOnClickListener {
            sendPhoneNumber()
        }
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_registration_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun showNetworkAvalibility() {
        val result = if (context == null) false else NetworkUtils.isNetworkAvailable(context!!)
        if (!result) Snackbar.make(
                constraintlayout_registration_base,
                resources.getString(R.string.all_nointernet),
                Snackbar.LENGTH_LONG
        ).show()
    }

    override fun sendPhoneNumber() {
        val udid = LoginUtils.getPhoneUdid(context!!)
        if (!edittext_registration_phonenumber.text.isNullOrEmpty()) {
            val phoneNumber =
                    resources.getString(R.string.all_irancodenumber) + edittext_registration_phonenumber.text!!.trim().toString()
            presenter.sendPhoneNumber(phoneNumber, udid)
        } else Snackbar.make(
                constraintlayout_registration_base,
                resources.getString(R.string.all_nophoneentered),
                Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showConfirmationPage(phoneNumber: String) {
        Handler().postDelayed({
            val intent = Intent(context, ConfirmationActivity::class.java)
            intent.putExtra(Constants.PHONE_NUMBER, phoneNumber)
            startActivity(intent)
            activity?.finish()
        }, 2500)

    }

}