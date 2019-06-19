package com.t2aq.wallet

interface BaseView<T> {

    var presenter: T


    fun firstSetup()
    fun initUiListeners()
}