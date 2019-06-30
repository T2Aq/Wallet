package com.t2aq.wallet.ui.addwallet

import com.t2aq.wallet.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AddWalletActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_addwallet)

    firstSetup()
  }

  fun firstSetup() {
    val mToolber = findViewById<Toolbar>(R.id.toolbar_addwallet)
    setSupportActionBar(mToolber)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    supportFragmentManager.beginTransaction().add(R.id.constrainlayout_addwallet_container, AddWalletFragment()).commit()
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }
}