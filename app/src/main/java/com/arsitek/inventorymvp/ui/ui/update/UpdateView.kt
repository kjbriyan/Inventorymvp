package com.arsitek.inventorymvp.ui.ui.update

import com.arsitek.inventorymvp.model.ResponseStatus

interface UpdateView {
    fun onShowloading()
    fun onHideloading()
    fun onDatasend(result : ResponseStatus?)
    fun onDataeror(t : Throwable)
}