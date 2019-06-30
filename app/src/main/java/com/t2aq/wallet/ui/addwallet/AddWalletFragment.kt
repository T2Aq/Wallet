package com.t2aq.wallet.ui.addwallet

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
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
        val addWalletDrawable = ContextCompat.getDrawable(context!!, R.drawable.addwallet)
        addWalletDrawable?.alpha = 150
        constraintlayout_addwallet_base.background = addWalletDrawable

        presenter = AddWalletPresenter(this)
        presenter.getCurrencyListFromServer()
    }

    override fun spinnerSetup(currencyNameList: List<String>) {
        val arrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, currencyNameList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_addwallet_curencies.adapter = arrayAdapter
    }

    override fun initUiListeners() {
        button_addwallet_add.setOnClickListener {
            val currencyCode = spinner_addwallet_curencies.selectedItem as String
            val walletName = textinputedittext_addwallet_walletname.text
            when {
                walletName.isNullOrEmpty() -> showResult(resources.getString(R.string.addwallet_selectcurrencyname))
                else ->

                    presenter.insertWallet(context!!, currencyCode, walletName.toString())
            }
        }
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_addwallet_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun finishAddWalletActivity() {
        Handler().postDelayed({ activity?.finish() }, 2500)
    }
}