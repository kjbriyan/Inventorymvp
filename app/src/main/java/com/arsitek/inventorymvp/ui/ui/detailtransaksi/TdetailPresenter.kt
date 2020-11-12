package com.arsitek.inventorymvp.ui.ui.detailtransaksi

import com.arsitek.inventorymvp.model.ResponseStatus
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TdetailPresenter(private val mView: TdetailView) {

    fun updatestatus(idTransaksi: String, status: String) {
        mView.onLoading()
        Initretrofit().getInstance().updatestatus(idTransaksi, status)
            .enqueue(object : Callback<ResponseStatus> {
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    mView.onSenddata(response.body())
                    mView.onHide()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onEror(t)
                    mView.onHide()
                }

            })
    }
    fun delete(idTransaksi: String) {
        mView.onLoading()
        Initretrofit().getInstance().deletetransaksi(idTransaksi)
            .enqueue(object : Callback<ResponseStatus> {
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    mView.onSenddata(response.body())
                    mView.onHide()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onEror(t)
                    mView.onHide()
                }

            })
    }
    fun retur(id: String,stock : String){
        mView.onLoading()
        Initretrofit().getInstance().returen(id,stock)
            .enqueue(object : Callback<ResponseStatus>{
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    mView.onSenddata(response.body())
                    mView.onHide()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onEror(t)
                    mView.onHide()
                }

            })
    }
}