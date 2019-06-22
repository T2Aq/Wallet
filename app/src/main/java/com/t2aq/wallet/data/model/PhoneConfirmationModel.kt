package com.t2aq.wallet.data.model

import com.google.gson.annotations.SerializedName

data class PhoneConfirmationModel(@SerializedName ("phone") val phone:String,
                                  @SerializedName ("udid") val udid:String,
                                  @SerializedName ("deviceName") val deviceName:String,
                                  @SerializedName ("deviceType") val deviceType:String,
                                  @SerializedName ("firebaseRegistrationId") val firebaseRegistrationId:String,
                                  @SerializedName ("activationCode") val activationCode:Int
                                  )