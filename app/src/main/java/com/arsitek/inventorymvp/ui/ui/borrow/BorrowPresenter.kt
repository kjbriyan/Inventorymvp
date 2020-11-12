package com.arsitek.inventorymvp.ui.ui.borrow

import com.arsitek.inventorymvp.model.ResponseStatus
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BorrowPresenter(private val mView : BorrowView) {

    fun sendTransaksi(idbarang : String,iduser : String,keterangan : String,pinjam : String){
        mView.onShowloading()
        Initretrofit().getInstance().createtransaksi(idbarang,iduser,keterangan,pinjam)
            .enqueue(object : Callback<ResponseStatus>{
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    mView.onDatasend(response.body())
                    mView.onHideloading()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onDataeror(t)
                    mView.onHideloading()
                }

            })
    }
}