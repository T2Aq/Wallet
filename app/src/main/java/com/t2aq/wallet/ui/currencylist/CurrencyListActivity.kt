package com.t2aq.wallet.ui.currencylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t2aq.wallet.R

class CurrencyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_currencylist)

        firstSetup()
    }

    private fun firstSetup() {
        supportFragmentManager.beginTransaction().add(R.id.constraintlayout_currencylist_container, CurrencyListFragment()).commit()
    }

}