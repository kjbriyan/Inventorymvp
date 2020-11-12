package com.arsitek.inventorymvp.ui.ui.history

import com.arsitek.inventorymvp.model.DataItemTransaksi

interface HistoryView {
    fun onShow()
    fun onHide()
    fun onDatashow(t: List<DataItemTransaksi?>)
    fun onDataeror(t: Throwable)
}