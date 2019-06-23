package com.t2aq.wallet.ui.mainpage

import com.t2aq.wallet.BasePresenter
import com.t2aq.wallet.BaseView

interface MainPageContract{

    interface View:BaseView<Presenter>{

    }

    interface Presenter:BasePresenter{

    }
}