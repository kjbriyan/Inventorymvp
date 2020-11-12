package com.arsitek.inventorymvp.ui.ui.borrow

import com.arsitek.inventorymvp.model.ResponseStatus

interface BorrowView {
    fun onShowloading()
    fun onHideloading()
    fun onDatasend(s : ResponseStatus?)
    fun onDataeror(t : Throwable)
}