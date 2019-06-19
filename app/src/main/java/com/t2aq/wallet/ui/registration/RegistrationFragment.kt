package com.t2aq.wallet.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.t2aq.wallet.R
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(), RegistrationContract.View {


    override lateinit var presenter: RegistrationContract.Presenter
    var udid = "D89707AC55BAED9E8F23B826FB2A28E96095A190"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()


    }


    override fun firstSetup() {
        presenter = RegistrationPresenter(this)

    }

    override fun initUiListeners() {
        button_registration_register.setOnClickListener {
            if (!edittext_registration_phonenumber.text.isNullOrEmpty())
                presenter.sendValidationCode(edittext_registration_phonenumber.text!!.trim().toString(),udid)
            else
                Toast.makeText(context, "no input", Toast.LENGTH_LONG).show()
            //TODO snackbar
        }
    }

}