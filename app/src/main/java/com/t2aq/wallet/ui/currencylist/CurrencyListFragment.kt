package com.t2aq.wallet.ui.currencylist

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.data.model.CurrencyModel
import kotlinx.android.synthetic.main.fragment_currencylist.*
import kotlinx.android.synthetic.main.toolbar_currencylist.*

class CurrencyListFragment : Fragment(), CurrencyListContract.View,
    CurrencyListAdapter.ViewCallbackInterface {


    override lateinit var presenter: CurrencyListContract.Presenter
    private lateinit var currencyListAdapter: CurrencyListAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencylist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()
    }

    override fun firstSetup() {
        presenter = CurrencyListPresenter(this)
        //adapter
        recyclerview_currencylist_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyListAdapter(this)
        recyclerview_currencylist_list.adapter = currencyListAdapter

        presenter.getCurrencyListFromServer()
    }

    override fun setRecyclerData(currencyList: List<CurrencyModel>) {
        progressbar_currencylist_progress.visibility = View.GONE
        currencyListAdapter.setData(currencyList)
        currencyListAdapter.notifyDataSetChanged()
    }


    override fun initUiListeners() {
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_currencylist_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun visibleAddButton() {
        val addAllButton = activity?.findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.imagebutton_currencylist_addall)
        addAllButton?.visibility = View.VISIBLE
        addAllButton?.setOnClickListener {
            presenter.insertCurrencyListToDatabase()
        }
    }

    override fun insertCurrencyCallback(currencyModel: CurrencyModel) {
        presenter.insertCurrencyToDatabase(currencyModel)
    }

    override fun showResultCallback(message: String) {
        showResult(message)
    }


}