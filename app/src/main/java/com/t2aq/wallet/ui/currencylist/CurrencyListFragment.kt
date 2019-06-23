package com.t2aq.wallet.ui.currencylist

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.data.model.CurrencyModel
import kotlinx.android.synthetic.main.activity_currencylist.*
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.fragment_currencylist.*

class CurrencyListFragment : Fragment(),CurrencyListContract.View {

    override lateinit var presenter: CurrencyListContract.Presenter
    private lateinit var currencyListAdapter:CurrencyListAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencylist,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
    }

    override fun firstSetup() {
        presenter = CurrencyListPresenter(this)
        //adapter
        recyclerview_currencylist_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyListAdapter()
        recyclerview_currencylist_list.adapter = currencyListAdapter

        presenter.getCurrencyListFromServer()
    }

    override fun setRecyclerData(currencyList: List<CurrencyModel>) {
        //test
        currencyListAdapter.setData(currencyList)
        currencyListAdapter.notifyDataSetChanged()
    }


    override fun initUiListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResult(result: String) {
        Snackbar.make(linearlayout_currencylist_base, result, Snackbar.LENGTH_LONG).show()
    }
}