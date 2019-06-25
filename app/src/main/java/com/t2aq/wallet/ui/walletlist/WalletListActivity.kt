package com.t2aq.wallet.ui.walletlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.addwallet.AddWalletActivity

class WalletListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_walletlist)

    firstSetup()
  }

  private fun firstSetup() {
    val fab: FloatingActionButton = findViewById(R.id.floatingactionbutton_walletlist_add)
    fab.setOnClickListener { view ->

      val intent = Intent(this, AddWalletActivity::class.java)
      startActivity(intent)
    }
    supportFragmentManager.beginTransaction().add(R.id.constrainlayout_walletlist_container, WalletListFragment()).commit()
  }
}