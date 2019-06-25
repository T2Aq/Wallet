package com.t2aq.wallet.ui.walletlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.t2aq.wallet.R
import com.t2aq.wallet.data.model.WalletModel
import kotlinx.android.synthetic.main.item_walletlist.view.*

class WalletListAdapter() : RecyclerView.Adapter<WalletListAdapter.ViewHolder>() {

  var walletList = emptyList<WalletModel>()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_walletlist, parent, false))
  }

  override fun getItemCount(): Int = walletList.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.itemView.textview_walletlist_name.text = walletList[position].name
    holder.itemView.textview_walletlist_currency.text = walletList[position].currencySymbol
  }

  fun setData(walletList: List<WalletModel>) {
    this.walletList = walletList
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}