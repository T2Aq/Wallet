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
            button_registration_register.isEnabled = false
            sendPhoneNumber()
        }
    }

    override fun showResult(result: String, showClickedButton:Boolean) {
        constraintlayout_registration_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
        if(showClickedButton)visibleClickedButton()

    }

    override fun showNetworkAvalibility() {
        val result = if (context == null) false
        else NetworkUtils.isNetworkAvailable(context!!)
        if (!result)
            showResult(resources.getString(R.string.all_nointernet),true)
    }

    override fun sendPhoneNumber() {
        val udid = context?.let { LoginUtils.getPhoneUdid(it) } ?: ""
        if (!edittext_registration_phonenumber.text.isNullOrEmpty()) {
            val phoneNumber =
                resources.getString(R.string.all_irancodenumber) + edittext_registration_phonenumber.text!!.trim().toString()
            presenter.sendPhoneNumber(phoneNumber, udid)
        } else showResult(resources.getString(R.string.all_nophoneentered),true)
    }

    override fun showConfirmationPage(phoneNumber: String) {
        Handler().postDelayed({
            val intent = Intent(context, ConfirmationActivity::class.java)
            intent.putExtra(Constants.PHONE_NUMBER, phoneNumber)
            startActivity(intent)
            visibleClickedButton()
            activity?.finish()
        }, 1500)

    }
     fun visibleClickedButton() {
        button_registration_register?.isEnabled = true
    }



}