package com.t2aq.wallet.ui.confirmation

import com.t2aq.wallet.R
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_confirmation)
        firstSetup()
    }

    private fun firstSetup() {
        supportFragmentManager.beginTransaction().add(R.id.constrainlayout_confirmation_container, ConfirmationFragment()).commit()
    }
}