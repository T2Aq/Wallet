package com.t2aq.wallet.data.remote

import com.t2aq.wallet.data.DbConstants
import com.t2aq.wallet.data.model.ConfirmationModel
import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.model.RegistrationModel
import com.t2aq.wallet.data.model.WalletModel
import com.t2aq.wallet.utils.LoginUtils
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @FormUrlEncoded
    @HTTP(method = "CLAIM", path = "clients", hasBody = true)
    fun claim(@Field("phone") phone: String, @Field("udid") udid: String): Call<RegistrationModel>

    @FormUrlEncoded
    @HTTP(method = "BIND", path = "clients", hasBody = true)
    fun bind(@Field("phone") phone: String, @Field("udid") udid: String, @Field("deviceName") deviceName: String, @Field(
        "activationCode") activationCode: Int): Call<ConfirmationModel>

    @HTTP(method = "LIST", path = "currencies", hasBody = true)
    fun currencyList(): Call<List<CurrencyModel>>

    @FormUrlEncoded
    @HTTP(method = "CREATE", path = "wallets", hasBody = true)
    fun insertWallet(@Header("Authorization") token:String, @Field(DbConstants.NAME_WALLET_MODEL) name: String, @Field(DbConstants.CURRENCY_CODE_WALLET_MODEL) currencyCode: String): Call<WalletModel>


    @HTTP(method = "LIST", path = "wallets", hasBody = true)
    fun walletList(@Header("Authorization") token:String): Call<List<WalletModel>>


//    @HTTP(method = "CLAIM", path = "/apiv1/clients")
//    fun claim(): Call<RegistrationModel>
    //@Part("phone") phone: String, @Part("udid") udid: String

//    @FormUrlEncoded
//    @HTTP(method = "BIND", path = "clients",hasBody = true)
//    fun bind()
}