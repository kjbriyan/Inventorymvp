package com.arsitek.inventorymvp.ui.ui.home

import android.widget.EditText
import com.arsitek.inventorymvp.model.DataItemBarang

interface HomeView {

    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results : MutableList<DataItemBarang>)
    fun onDataeror (message : Throwable)


}