package com.t2aq.wallet.ui.mainpage

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.currencylist.CurrencyListActivity
import com.t2aq.wallet.ui.exchange.ExchangeActivity
import com.t2aq.wallet.ui.registration.RegistrationActivity
import com.t2aq.wallet.ui.walletlist.WalletListActivity
import com.t2aq.wallet.utils.LoginUtils

class MainPageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)


        firstSetup()

    }

    private fun firstSetup() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_mainpage_toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout_mainpage_navigationdrawer)
        val navView: NavigationView = findViewById(R.id.navigationview_mainpage_navigationview)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.mainpage_navigationdraweropen,
            R.string.mainpage_navigationdrawerclose
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.constrainlayout_mainpage_container, MainPageFragment()).commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout_mainpage_navigationdrawer)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.item_navigationdrawer_currencylist -> {
                val intent = Intent(this, CurrencyListActivity::class.java)
                startActivity(intent)
            }
            R.id.item_navigationdrawer_exchange -> {
                val intent = Intent(this, ExchangeActivity::class.java)
                startActivity(intent)
            }
            R.id.item_navigationdrawer_wallets -> {
                val intent = Intent(this, WalletListActivity::class.java)
                startActivity(intent)

            }
            R.id.item_navigationdrawer_signout -> {
                LoginUtils.saveTokenInSharedPreferences("")
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout_mainpage_navigationdrawer)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
