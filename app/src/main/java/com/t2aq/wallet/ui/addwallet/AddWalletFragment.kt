package com.t2aq.wallet.ui.addwallet

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_addwallet.*

class AddWalletFragment : Fragment(), AdapterView.OnItemSelectedListener {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_addwallet, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val addWalletDrawable = ContextCompat.getDrawable(context!!,R.drawable.addwallet)
    addWalletDrawable?.alpha = 150
    constraintlayout_addwallet_base.background = addWalletDrawable
    val c = arrayListOf<String>("$","&")
    spinnerSetup(c)
  }

  fun spinnerSetup( currencyList: List<String>) {
    spinner_addwallet_curencies.onItemSelectedListener = this
    val arrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item,currencyList)
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner_addwallet_curencies.adapter = arrayAdapter
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {

  }

  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

  }
}