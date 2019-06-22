package com.t2aq.wallet.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.registration.RegistrationActivity

class MainFragment : Fragment(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        firstSetup()
        context?.let { presenter.isUserSignedIn(it) }

    }

    override fun firstSetup() {
        presenter = MainPresenter(this)
    }

    override fun initUiListeners() {
    }

    override fun getToken(token: String?) {
        //if user have token go to main form
        //if not go to registration form
        if (token.isNullOrEmpty()) {
            val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
        } else {
            //ToDo main form
        }
    }

    override fun showResult(result: String) {
    }

}