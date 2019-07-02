package com.t2aq.wallet.ui.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t2aq.wallet.R

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration)

        firstSetup()
    }

    private fun firstSetup() {
        supportFragmentManager.beginTransaction()
            .add(R.id.constrainlayout_registration_container, RegistrationFragment()).commit()
    }

}