package com.t2aq.wallet.utils

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.t2aq.wallet.ui.registration.RegistrationContract
import android.provider.SyncStateContract.Helpers.update
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and


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


    fun saveTokenInSharedPreferences() {
        //TODO
    }

    fun hasToken(): Boolean {
        return true
    }
}