package com.t2aq.wallet.ui.registration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.confirmation.ConfirmationActivity
import com.t2aq.wallet.utils.CommonUtils
import com.t2aq.wallet.utils.Constants
import com.t2aq.wallet.utils.LoginUtils
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
            progressbar_registration_progress.bringToFront()
            progressbar_registration_progress.visibility = View.VISIBLE
            sendPhoneNumber()
        }

        edittext_registration_phonenumber.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.ACTION_DOWN) {
                button_registration_register.callOnClick()
                activity?.let { CommonUtils.hideSoftKeyboard(it) }
                true
            } else
                false

        }
    }

    override fun showResult(result: String, showClickedButton: Boolean) {
        constraintlayout_registration_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
        if (showClickedButton) visibleClickedButton()

    }

    override fun showNetworkAvalibility() {
        val result = if (context == null) false
        else CommonUtils.isNetworkAvailable(context!!)
        if (!result)
            showResult(resources.getString(R.string.all_nointernet), true)
    }

    override fun sendPhoneNumber() {
        val udid = context?.let { LoginUtils.getPhoneUdid(it) } ?: ""
        if (!edittext_registration_phonenumber.text.isNullOrEmpty()) {
            val phoneNumber =
                resources.getString(R.string.all_irancodenumber) + edittext_registration_phonenumber.text!!.trim().toString()
            presenter.sendPhoneNumber(phoneNumber, udid)
        } else showResult(resources.getString(R.string.all_nophoneentered), true)
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
        progressbar_registration_progress?.visibility = View.INVISIBLE
        button_registration_register?.isEnabled = true
    }


}