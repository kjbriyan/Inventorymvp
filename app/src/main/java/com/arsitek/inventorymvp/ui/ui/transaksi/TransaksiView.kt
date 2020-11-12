package com.arsitek.inventorymvp.ui.ui.transaksi
import com.arsitek.inventorymvp.model.DataItemTransaksi


interface TransaksiView {
    fun onLoading()
    fun onHide()
    fun onSukses(t :  List<DataItemTransaksi?>)
    fun onEror(t : Throwable)
}