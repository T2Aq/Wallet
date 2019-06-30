package com.t2aq.wallet.data.model

import com.google.gson.annotations.SerializedName
import com.t2aq.wallet.utils.DbConstants

data class WalletModel(@SerializedName(DbConstants.ID_WALLET_MODEL) val id: String?,
                       @SerializedName(DbConstants.NAME_WALLET_MODEL) val name: String?,
                       @SerializedName(DbConstants.CURRENCY_CODE_ALL) val currencyCode: String?,
                       @SerializedName(DbConstants.CURRENCY_SYMBOL_WALLET_MODEL) val currencySymbol: String?)