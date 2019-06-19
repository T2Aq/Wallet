package com.t2aq.wallet.ui.registration

import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.model.PhoneInputModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationPresenter(val registrationView:RegistrationContract.View):RegistrationContract.Presenter{
    override fun sendValidationCode(phone: String, udid: String) {
        APIClient.getService()?.claim(phone,udid)?.enqueue(object : Callback<PhoneInputModel>{
            override fun onFailure(call: Call<PhoneInputModel>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<PhoneInputModel>,
                response: Response<PhoneInputModel>
            ) {
                //TODO registrationView.se
            }

        })
    }


}