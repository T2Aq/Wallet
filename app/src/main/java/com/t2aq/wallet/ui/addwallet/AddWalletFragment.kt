package com.t2aq.wallet.ui.addwallet

import com.t2aq.wallet.R
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_addwallet.*

class AddWalletFragment : Fragment(), AddWalletContract.View, AdapterView.OnItemSelectedListener {

    override lateinit var presenter: AddWalletContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_addwallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addWalletDrawable = ContextCompat.getDrawable(context!!, R.drawable.addwallet)
        addWalletDrawable?.alpha = 150
        constraintlayout_addwallet_base.background = addWalletDrawable

        firstSetup()
        initUiListeners()
    }

    override fun firstSetup() {
        presenter = AddWalletPresenter(this)
        presenter.getCurrencyListFromServer()
    }

    override fun spinnerSetup(currencyNameList: List<String>) {
        spinner_addwallet_curencies.onItemSelectedListener = this
        val arrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, currencyNameList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_addwallet_curencies.adapter = arrayAdapter
    }

    override fun initUiListeners() {
        button_addwallet_add.setOnClickListener {
            val currencyCode = spinner_addwallet_curencies.selectedItem as String
            val walletName =textinputedittext_addwallet_walletname.text
            when {
                walletName.isNullOrEmpty() -> showResult(resources.getString(R.string.addwallet_selectcurrencyname))
                else -> presenter.insertWallet(currencyCode,walletName.toString())
            }
        }
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_addwallet_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun finishAddWalletActivity() {
        Handler().postDelayed({activity?.finish()},2500)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}