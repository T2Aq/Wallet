package com.t2aq.wallet.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(@SerializedName("name") val name: String?,
                         @SerializedName("code") val code: String?,
                         @SerializedName("symbol") val symbol: String?)