package com.t2aq.wallet.data.model

import com.google.gson.annotations.SerializedName
import com.t2aq.wallet.utils.DbConstants

data class RateModel(@SerializedName(DbConstants.CURRENCY_CODE_ALL) val currencyCode:String,
                     @SerializedName(DbConstants.BUY_RATE_MODEL) val buy:Float)