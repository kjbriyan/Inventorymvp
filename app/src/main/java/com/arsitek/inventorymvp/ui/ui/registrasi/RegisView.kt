package com.arsitek.inventorymvp.ui.ui.registrasi

import com.arsitek.inventorymvp.model.ResponseStatus

interface RegisView {
    fun onLoading()
    fun onHideloading()
    fun onDatasukses(t : ResponseStatus?)
    fun onDataeror(t : Throwable)
}