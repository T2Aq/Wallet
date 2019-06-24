package com.t2aq.wallet.ui.walletlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.currencylist.CurrencyListFragment

class WalletListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_walletlist)

        firstSetup()
    }

    private fun firstSetup() {
        supportFragmentManager.beginTransaction().add(R.id.constrainlayout_walletlist_container, WalletListFragment()).commit()
    }
}