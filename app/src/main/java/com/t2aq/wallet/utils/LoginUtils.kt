package com.t2aq.wallet.utils

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.t2aq.wallet.WalletApplication
import java.security.MessageDigest


object LoginUtils {

    fun getPhoneUdid(context: Context): String {
        val phoneId = Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

        val udid = hashString(Constants.SHA_1, phoneId)
        Log.v("udid:", udid)
        //
        return udid
    }

    private fun hashString(type: String, input: String): String {
        val bytes = MessageDigest.getInstance(type).digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(Constants.HEXCHARS[i shr 4 and 0x0f])
            result.append(Constants.HEXCHARS[i and 0x0f])
        }

        return result.toString()
    }


    fun saveTokenInSharedPreferences(token: String) {
        val editor = WalletApplication.instance.getSharedPreferences(
            Constants.SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
        editor.putString(Constants.TOKEN, token)
        editor.apply()
    }

    fun getTokenFromSharedPreferences(): String? {
        val prefs = WalletApplication.instance.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return prefs.getString(Constants.TOKEN, "")
    }


}