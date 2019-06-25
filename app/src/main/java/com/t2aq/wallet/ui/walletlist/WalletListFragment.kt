package com.t2aq.wallet.ui.walletlist

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.t2aq.wallet.data.model.WalletModel
import kotlinx.android.synthetic.main.fragment_walletlist.*

class WalletListFragment : Fragment(), WalletListContract.View {

  private lateinit var walletListAdapter: WalletListAdapter

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_walletlist, container, false)
  }

  override fun setRecyclerData(walletList: List<WalletModel>) {
    walletListAdapter.setData(walletList)
    walletListAdapter.notifyDataSetChanged()
  }

  override lateinit var presenter: WalletListContract.Presenter

  override fun firstSetup() {
    presenter = WalletListPresenter(this)

    recyclerview_walletlist_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    walletListAdapter = WalletListAdapter()
    recyclerview_walletlist_list.adapter = walletListAdapter
  }

  override fun initUiListeners() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showResult(result: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}