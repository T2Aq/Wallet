package com.t2aq.wallet.ui.walletlist

import com.t2aq.wallet.data.model.WalletModel
import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface WalletListContract {

    interface View : BaseView<Presenter> {
        fun setRecyclerData(walletList: List<WalletModel>)
    }

    interface Presenter : BasePresenter {

        fun getWalletListFromServer()
    }
}