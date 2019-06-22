package com.t2aq.wallet.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils{

     fun isNetworkAvailable(context: Context):Boolean{

         val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         try {
             val activeNetwork = connectivityManager.activeNetworkInfo
             return activeNetwork!=null && activeNetwork.isConnected
         } catch (e: Exception) {
             e.printStackTrace()
             return false
         }
     }
}