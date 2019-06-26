package com.t2aq.wallet.ui.exchange

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_addwallet.*
import kotlinx.android.synthetic.main.fragment_exchange.*

class ExchangeFragment:Fragment(),ExchangeContract.View {
    override lateinit var presenter: ExchangeContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstSetup()
    }

    override fun firstSetup() {
        presenter = ExchangePresenter(this)
        presenter.getCurrencyListFromServer()
    }

    override fun initUiListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_exchange_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun spinnerSetup(currencyNameList: List<String>) {
        val arrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, currencyNameList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_exchange_curencies.adapter = arrayAdapter
    }
}