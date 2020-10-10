package com.arsitek.inventorymvp.ui.ui.additem

import com.arsitek.inventorymvp.model.ResponseStatus

interface AddItemView {
    fun onShowloading()
    fun onHideloading()
    fun onDatasend(result : ResponseStatus?)
    fun onDataeror(t : Throwable)
}