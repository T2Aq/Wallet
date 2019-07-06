package com.t2aq.wallet.ui.mainpage

import com.t2aq.wallet.ui.BasePresenter
import com.t2aq.wallet.ui.BaseView

interface MainPageContract{

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter
}