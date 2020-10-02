package com.arsitek.inventorymvp.ui.ui.login

import com.arsitek.inventorymvp.model.DataItem



interface LoginView{
    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItem?>)
    fun onDataeror (message : Throwable)

}