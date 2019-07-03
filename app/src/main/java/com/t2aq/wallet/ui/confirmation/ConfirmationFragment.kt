package com.t2aq.wallet.ui.confirmation

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.mainpage.MainPageActivity
import com.t2aq.wallet.ui.registration.RegistrationActivity
import com.t2aq.wallet.utils.CommonUtils
import com.t2aq.wallet.utils.Constants
import com.t2aq.wallet.utils.LoginUtils
import kotlinx.android.synthetic.main.fragment_confirmation.*

class ConfirmationFragment : Fragment(), ConfirmationContract.View {

    override lateinit var presenter: ConfirmationContract.Presenter
    override lateinit var phoneNumber: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()

    }

    override fun firstSetup() {
        presenter = ConfirmationPresenter(this)
        phoneNumber = arguments?.getString(Constants.PHONE_NUMBER) ?: ""
        Log.v("show", "in confirmation fragment")
    }

    override fun initUiListeners() {
        button_confirmation_confirm.setOnClickListener {
            button_confirmation_confirm.isEnabled = false
            progressbar_confirmation_progress.bringToFront()
            progressbar_confirmation_progress.visibility = View.VISIBLE
            sendConfirmationCode()
        }

        textinputedittext_confirmation_validationcode.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.ACTION_DOWN) {
                button_confirmation_confirm.callOnClick()
                activity?.let { CommonUtils.hideSoftKeyboard(it) }
                true
            } else
                false

        }
        textview_confirmation_retry.setOnClickListener { showRetryPage() }

    }

    override fun showResult(result: String, showClickedButton: Boolean) {
        constraintlayout_confirmation_base?.let {
            Snackbar.make(it, result, Snackbar.LENGTH_LONG).show()
            if (showClickedButton) visibleClickedButton()
        }

    }

    override fun showMainPage() {
        val intent = Intent(context, MainPageActivity::class.java)
        startActivity(intent)
        visibleClickedButton()
        activity?.finish()
    }

    override fun sendConfirmationCode() {
        val phoneInputText = textinputedittext_confirmation_validationcode.text
        if (!phoneInputText.isNullOrEmpty()) {
            val udid = context?.let { LoginUtils.getPhoneUdid(it) } ?: ""
            val deviceName = android.os.Build.MODEL
            val activationCode =
                textinputedittext_confirmation_validationcode.text?.trim().toString().toInt()
            presenter.sendVerificationCode(phoneNumber, udid, deviceName, activationCode)
        } else {
            showResult(resources.getString(R.string.confirmation_noverificationcode), true)
        }
    }

    private fun visibleClickedButton() {
        progressbar_confirmation_progress?.visibility = View.INVISIBLE
        button_confirmation_confirm?.isEnabled = true
    }

    private fun showRetryPage(){
        val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
            activity?.finish()
    }


}