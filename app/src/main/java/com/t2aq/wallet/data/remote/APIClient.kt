package com.t2aq.wallet.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {

    val BASE_URL = "http://nightly-alpha.carrene.com/apiv1/"
    lateinit var retrofit: Retrofit

    fun getClient(){
         retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): InterfaceAPI? {
        getClient()
        return retrofit.create(InterfaceAPI::class.java)
    }
}