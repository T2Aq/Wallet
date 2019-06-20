package com.t2aq.wallet.data.remote

import com.t2aq.wallet.data.model.PhoneInputModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @FormUrlEncoded
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    @HTTP(method = "CLAIM", path = "/apiv1/clients", hasBody = true)
    fun claim(@Field("phone") phone: String, @Field("udid") udid: String): Call<PhoneInputModel>


//    @HTTP(method = "CLAIM", path = "/apiv1/clients")
//    fun claim(): Call<PhoneInputModel>
    //@Part("phone") phone: String, @Part("udid") udid: String

//    @FormUrlEncoded
//    @HTTP(method = "BIND", path = "clients",hasBody = true)
//    fun bind()
}