package com.arsitek.inventorymvp.ui.ui.detailtransaksi

import com.arsitek.inventorymvp.model.ResponseStatus

interface TdetailView {
    fun onLoading()
    fun onHide()
    fun onSenddata(t : ResponseStatus?)
    fun onEror(t : Throwable)
}