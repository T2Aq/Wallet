package com.t2aq.wallet.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.t2aq.wallet.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstSetup()
    }

        private fun firstSetup() {
        supportFragmentManager.beginTransaction().add(R.id.constraintlayout_main_container, MainFragment()).commit()
    }
}
