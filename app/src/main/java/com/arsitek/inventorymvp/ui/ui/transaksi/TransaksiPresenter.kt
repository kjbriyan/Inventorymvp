package com.arsitek.inventorymvp.ui.ui.transaksi

import com.arsitek.inventorymvp.model.ResponseTransaksi
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiPresenter(private val mView: TransaksiView) {

    fun getData(){
        mView.onLoading()
        Initretrofit().getInstance().getTransaksi()
            .enqueue(object : Callback<ResponseTransaksi>{
                override fun onResponse(
                    call: Call<ResponseTransaksi>,
                    response: Response<ResponseTransaksi>
                ) {
                    mView.onSukses(response.body()?.data ?: emptyList())
                    mView.onHide()
                }

                override fun onFailure(call: Call<ResponseTransaksi>, t: Throwable) {
                    mView.onEror(t)
                    mView.onHide()

                }

            })
    }
}