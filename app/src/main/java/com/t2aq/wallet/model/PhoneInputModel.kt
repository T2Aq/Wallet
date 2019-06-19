package com.t2aq.wallet.model

import com.google.gson.annotations.SerializedName

data class PhoneInputModel( @SerializedName("id") val id: Int,
 @SerializedName("name") val name: Int,
 @SerializedName("phone") val phone: Int,
 @SerializedName("udid") val udid: Int)
