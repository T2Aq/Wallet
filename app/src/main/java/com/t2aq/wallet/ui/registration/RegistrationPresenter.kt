package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.data.model.RegistrationModel
import com.t2aq.wallet.data.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationPresenter(val registrationView: RegistrationContract.View) :
    RegistrationContract.Presenter {


    override fun sendPhoneNumber(phoneNumber: String, udid: String) {
        APIClient.getService()?.claim(phoneNumber, udid)?.enqueue(object : Callback<RegistrationModel> {

            override fun onFailure(call: Call<RegistrationModel>, t: Throwable) {
                val result = "failed: " + t.message
                registrationView.showResult(result,true)
            }

            override fun onResponse(
                call: Call<RegistrationModel>,
                response: Response<RegistrationModel>
            ) {
                val result = "responsed: " + response.message()

                if (response.code() == 200) {
                    registrationView.showConfirmationPage(phoneNumber)
                    registrationView.showResult(result,false)
                }
                else
                    registrationView.showResult(result,true)
            }

        })
    }

}