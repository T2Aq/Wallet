package com.t2aq.wallet.utils

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.t2aq.wallet.WalletApplication
import java.security.MessageDigest


object LoginUtils {

    fun getPhoneUdid(context: Context): String {
        val phoneId = Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

        val udid = hashString(Constants.sha1, phoneId)
        Log.v("udid:", udid)
        //
        return udid
    }

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest.getInstance(type).digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }


    fun saveTokenInSharedPreferences(token:String) {
    val editor = WalletApplication.instance.getSharedPreferences(Constants.sharedPreferences,
                                                  Context.MODE_PRIVATE).edit()
        editor.putString(Constants.sTOKEN, token)
        editor.apply()
    }

    fun hasTokenFromSharedPreferences(): String? {
        val prefs = WalletApplication.instance.getSharedPreferences(Constants.sharedPreferences, Context.MODE_PRIVATE)
        return prefs.getString(Constants.sTOKEN, "")
    }
}