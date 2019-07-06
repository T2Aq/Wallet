package com.t2aq.wallet.ui

interface BaseView<T> {

    var presenter: T


    fun firstSetup()
    fun initUiListeners()
    fun showResult(result: String, showClickedButton: Boolean)

}