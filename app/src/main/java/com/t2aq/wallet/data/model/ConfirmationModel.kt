package com.t2aq.wallet.data.model

import com.google.gson.annotations.SerializedName

data class ConfirmationModel(@SerializedName ("createdAt") val createdAt:String?,
                             @SerializedName ("type") val type:String?,
                             @SerializedName ("isAdmin") val isAdmin:Boolean?,
                             @SerializedName ("baseCurrencyCode") val baseCurrencyCode:String?,
                             @SerializedName ("hmacSecret") val hmacSecret:String?,
                             @SerializedName ("id") val id:Int?,
                             @SerializedName ("email") val email:String?,
                             @SerializedName ("deviceName") val deviceName:String?,
                             @SerializedName ("phone") val phone:String?,
                             @SerializedName ("udid") val udid:String?,
                             @SerializedName ("isActive") val isActive:Boolean?,
                             @SerializedName ("baseCurrencySymbol") val baseCurrencySymbol:String?,
                             @SerializedName ("token") val token:String,
                             @SerializedName ("isNewClient") val isNewClient:Boolean)