package com.t2aq.wallet.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.t2aq.wallet.R
import com.t2aq.wallet.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(), RegistrationContract.View {


    override lateinit var presenter: RegistrationContract.Presenter
    var udid = "D89707AC55BAED9E8F23B826FB2A28E96095A190"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()


    }


    override fun firstSetup() {
        presenter = RegistrationPresenter(this)
        showNetworkAvalibility()

    }

    override fun initUiListeners() {
        button_registration_register.setOnClickListener {
            if (!edittext_registration_phonenumber.text.isNullOrEmpty()) {
                val phoneNumber = resources.getString(R.string.all_irancodenumber)+
                edittext_registration_phonenumber.text!!.trim().toString()

                presenter.sendPhoneNumber(phoneNumber, udid)
            } else
                Snackbar.make(
                    constraintlayout_registration_base,
                    resources.getString(R.string.all_nophoneentered),
                    Snackbar.LENGTH_LONG
                ).show()
        }
    }

    override fun showResult(result: String) {
        Snackbar.make(constraintlayout_registration_base, result, Snackbar.LENGTH_LONG).show()
    }

    override fun showNetworkAvalibility() {
        val result = if (context == null) false else NetworkUtils.isNetworkAvailable(context!!)
        if (!result)
            Snackbar.make(
                constraintlayout_registration_base, resources
                    .getString(R.string.all_nointernet), Snackbar.LENGTH_LONG
            ).show()
        else
            Snackbar.make(constraintlayout_registration_base, "internet OK",Snackbar.LENGTH_LONG).show()
    }
}