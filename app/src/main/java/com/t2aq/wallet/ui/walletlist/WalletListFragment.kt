package com.t2aq.wallet.ui.walletlist

import com.t2aq.wallet.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.data.model.WalletModel
import kotlinx.android.synthetic.main.fragment_addwallet.*
import kotlinx.android.synthetic.main.fragment_walletlist.*

class WalletListFragment : Fragment(), WalletListContract.View {

    private lateinit var walletListAdapter: WalletListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_walletlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstSetup()
    }

    override fun setRecyclerData(walletList: List<WalletModel>) {
        walletListAdapter.setData(walletList)
        walletListAdapter.notifyDataSetChanged()
    }

    override lateinit var presenter: WalletListContract.Presenter

    override fun firstSetup() {
        presenter = WalletListPresenter(this)

        val addWalletDrawable = ContextCompat.getDrawable(context!!, R.drawable.walletlist)
        addWalletDrawable?.alpha = 150
        constraintlayout_walletlist_base.background = addWalletDrawable

        recyclerview_walletlist_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        walletListAdapter = WalletListAdapter()
        recyclerview_walletlist_list.adapter = walletListAdapter


    }

    override fun initUiListeners() {
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_walletlist_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        presenter.getWalletListFromServer()
    }
}