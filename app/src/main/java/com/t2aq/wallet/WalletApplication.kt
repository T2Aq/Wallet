package com.t2aq.wallet

import android.app.Application
import com.facebook.stetho.Stetho

class WalletApplication :Application(){


    companion object {
        lateinit var instance: WalletApplication
        //private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Stetho.initializeWithDefaults(this)
    }
}