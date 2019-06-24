package com.t2aq.wallet.data.remote

import com.t2aq.wallet.data.model.ConfirmationModel
import com.t2aq.wallet.data.model.CurrencyModel
import com.t2aq.wallet.data.model.RegistrationModel
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


//    @HTTP(method = "CLAIM", path = "/apiv1/clients")
//    fun claim(): Call<RegistrationModel>
    //@Part("phone") phone: String, @Part("udid") udid: String

//    @FormUrlEncoded
//    @HTTP(method = "BIND", path = "clients",hasBody = true)
//    fun bind()
}