package com.t2aq.wallet.ui.addwallet

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_addwallet.*

class AddWalletFragment : Fragment(), AddWalletContract.View {

    override lateinit var presenter: AddWalletContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_addwallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()
    }

    override fun firstSetup() {
        presenter = AddWalletPresenter(this)
        presenter.getCurrencyListFromServer()
        progressbar_addwallet_forcurrencylist?.bringToFront()
        button_addwallet_add?.isEnabled = false
    }

    override fun spinnerSetup(currencyNameList: List<String>) {
        if (context != null) {
            val arrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, currencyNameList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_addwallet_curencies.adapter = arrayAdapter
        }
    }

    override fun initUiListeners() {
        button_addwallet_add.setOnClickListener {
            button_addwallet_add.isEnabled = false
            progressbar_addwallet_foraddwallet.bringToFront()
            progressbar_addwallet_foraddwallet.visibility = View.VISIBLE
            val currencyCode = spinner_addwallet_curencies.selectedItem as String
            val walletName = textinputedittext_addwallet_walletname.text
            when {
                walletName.isNullOrEmpty() -> showResult(
                    resources.getString(R.string.addwallet_selectcurrencyname),
                    true
                )
                else ->

                    context?.let { presenter.insertWallet(it, currencyCode, walletName.toString()) }
            }
        }

        textinputedittext_addwallet_walletname.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                button_addwallet_add.callOnClick()
                activity?.let { CommonUtils.hideSoftKeyboard(it) }
                true
            } else
                false

        }
    }

    override fun showResult(result: String, showClickedButton: Boolean) {
        constraintlayout_addwallet_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_SHORT).show() }
        if (showClickedButton) visibleClickedButton()
    }

    override fun finishAddWalletActivity() {
        Handler().postDelayed({
            activity?.finish()
        }, 2000)
    }

    fun visibleClickedButton() {
        progressbar_addwallet_forcurrencylist.visibility = View.INVISIBLE
        progressbar_addwallet_foraddwallet.visibility = View.INVISIBLE
        button_addwallet_add?.isEnabled = true
    }

    override fun onStart() {
        super.onStart()
        visibleClickedButton()
    }

}