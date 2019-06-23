package com.t2aq.wallet.ui.mainpage

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MainPageFragment:Fragment(),MainPageContract.View {
    override lateinit var presenter: MainPageContract.Presenter


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mainpage,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstSetup()
    }
    override fun firstSetup() {
       presenter = MainPagePresenter()
    }

    override fun initUiListeners() {
    }

    override fun showResult(result: String) {
    }

}