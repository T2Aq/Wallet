package com.t2aq.wallet.ui.registration

import android.content.res.Resources
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.data.model.PhoneInputModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.t2aq.wallet.R
import com.t2aq.wallet.data.remote.APIClient2
import com.t2aq.wallet.utils.NetworkUtils

class RegistrationPresenter(val registrationView:RegistrationContract.View):RegistrationContract.Presenter{


    override fun sendValidationCode(phone: String, udid: String) {
        APIClient2.getService()?.claim(phone,udid)?.enqueue(object : Callback<PhoneInputModel>{

            override fun onFailure(call: Call<PhoneInputModel>, t: Throwable) {
                val result = "failed: "+t.message
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