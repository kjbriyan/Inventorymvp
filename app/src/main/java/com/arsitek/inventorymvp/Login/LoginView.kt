package com.arsitek.inventorymvp.Login

import com.arsitek.inventorymvp.Model.DataItem



interface LoginView{
    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItem?>)
    fun onDataeror (message : Throwable)

}