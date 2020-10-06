package com.arsitek.inventorymvp.ui.ui.home

import com.arsitek.inventorymvp.model.DataItemBarang

interface HomeView {

    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : List<DataItemBarang?>)
    fun onDataeror (message : Throwable)

}