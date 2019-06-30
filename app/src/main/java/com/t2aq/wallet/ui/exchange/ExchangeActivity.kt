package com.t2aq.wallet.ui.exchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.t2aq.wallet.R


class ExchangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)
        firstSetup()
    }

    fun firstSetup() {
        val mToolber = findViewById<Toolbar>(R.id.toolbar_exchange)
        setSupportActionBar(mToolber)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportFragmentManager.beginTransaction().add(R.id.constrainlayout_exchange_container, ExchangeFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}