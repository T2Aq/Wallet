package com.t2aq.wallet.ui.confirmation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t2aq.wallet.R
import com.t2aq.wallet.utils.Constants

class ConfirmationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_confirmation)
        firstSetup()
    }

    private fun firstSetup() {
        val phoneNumber = intent.extras?.getString(Constants.PHONE_NUMBER)
        val bundle =Bundle()
        bundle.putString(Constants.PHONE_NUMBER, phoneNumber)
        val fragment = ConfirmationFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.constrainlayout_confirmation_container, fragment).commit()
    }
}