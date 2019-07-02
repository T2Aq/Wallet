package com.t2aq.wallet.ui.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.utils.Constants
import kotlinx.android.synthetic.main.fragment_exchange.*


class ExchangeFragment : Fragment(), ExchangeContract.View, AdapterView.OnItemSelectedListener {


    override lateinit var presenter: ExchangeContract.Presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstSetup()
        initUiListeners()
    }

    override fun firstSetup() {
        presenter = ExchangePresenter(this)
        presenter.getRateListFromServer()
    }

    override fun initUiListeners() {


        edittext_exchange_source.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edittext_exchange_source.hasFocus() && spinner_exchange_curencies.selectedItem != null) {
                    //
                    val currencyCode = spinner_exchange_curencies.selectedItem as String
                    if (s.toString().isNotEmpty()) {
                        val input = s.toString().toFloat()
                        presenter.calculateFromSource(input, currencyCode)
                    }
                }
            }


        })

        edittext_exchange_source.setOnFocusChangeListener { view: View?, hasFocus: Boolean ->
            if (hasFocus)
                edittext_exchange_source?.setText("")
        }



        edittext_exchange_destination.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edittext_exchange_destination.hasFocus() && spinner_exchange_curencies.selectedItem != null) {
                    //
                    val currencyCode = spinner_exchange_curencies.selectedItem as String
                    if (s.toString().isNotEmpty()) {
                        val input = s.toString().toFloat()
                        presenter.calculateFromDestination(input, currencyCode)
                    }

                }
            }
        })
        edittext_exchange_destination.setOnFocusChangeListener { view: View?, hasFocus: Boolean ->
            if (hasFocus)
                edittext_exchange_destination?.setText("")
        }
    }


    override fun showResult(result: String, showClickedButton: Boolean) {

        constraintlayout_exchange_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
    }

    override fun spinnerSetup(currencyNameList: List<String>) {
        spinner_exchange_curencies?.let {
            it.onItemSelectedListener = this
            val arrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, currencyNameList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            it.adapter = arrayAdapter
        }
    }

    override fun setText(result: Float, flag: String) {
        when (flag) {
            Constants.SOURCE -> edittext_exchange_source.setText(result.toString())
            Constants.DESTINATION -> edittext_exchange_destination.setText(result.toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        edittext_exchange_source.setText("")
        edittext_exchange_destination.setText("")
    }

}