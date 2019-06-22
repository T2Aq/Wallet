package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.data.model.PhoneInputModel
import com.t2aq.wallet.data.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationPresenter(val registrationView: RegistrationContract.View) :
    RegistrationContract.Presenter {


    override fun sendValidationCode(phone: String, udid: String) {
        APIClient.getService()?.claim(phone, udid)?.enqueue(object : Callback<PhoneInputModel> {

            override fun onFailure(call: Call<PhoneInputModel>, t: Throwable) {
                val result = "failed: " + t.message
                registrationView.showResult(result)
            }

            override fun onResponse(
                call: Call<PhoneInputModel>,
                response: Response<PhoneInputModel>
            ) {
                val result = "responsed: " + response.message()
                registrationView.showResult(result)
            }

        })
    }

}