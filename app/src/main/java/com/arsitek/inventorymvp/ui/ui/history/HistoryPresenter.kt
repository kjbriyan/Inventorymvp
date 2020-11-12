package com.arsitek.inventorymvp.ui.ui.history

import com.arsitek.inventorymvp.model.ResponseTransaksi
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryPresenter(private val mView : HistoryView) {


    fun filter(bulan : String){
        mView.onShow()
        Initretrofit().getInstance().filter(bulan)
            .enqueue(object : Callback<ResponseTransaksi>{
                override fun onResponse(
                    call: Call<ResponseTransaksi>,
                    response: Response<ResponseTransaksi>
                ) {
                    mView.onDatashow(response.body()?.data ?: emptyList())
                    mView.onHide()

                }

                override fun onFailure(call: Call<ResponseTransaksi>, t: Throwable) {
                    mView.onDataeror(t)
                    mView.onHide()
                }

            })
    }

}