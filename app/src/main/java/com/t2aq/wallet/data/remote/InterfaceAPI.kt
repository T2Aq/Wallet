package com.t2aq.wallet.data.remote

import com.t2aq.wallet.model.PhoneInputModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP

interface InterfaceAPI {

    @FormUrlEncoded
    @HTTP(method = "CLAIM", path = "clients")
    fun claim(@Field("phone") phone: String, @Field("udid") udid: String): Call<PhoneInputModel>
}