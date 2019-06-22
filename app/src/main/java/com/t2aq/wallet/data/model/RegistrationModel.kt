package com.t2aq.wallet.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String,
    @SerializedName("udid") val udid: String,
    @SerializedName("firebaseToken") val firebaseToken: String?
)
