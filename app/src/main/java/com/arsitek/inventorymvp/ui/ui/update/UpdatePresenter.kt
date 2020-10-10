package com.arsitek.inventorymvp.ui.ui.update

import com.arsitek.inventorymvp.model.ResponseStatus
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePresenter(private val mView: UpdateView) {
    private val TAG = "Update"

    fun updateData(id: String, nama: String, jumlah: String, img: String?) {
        mView.onShowloading()
        Initretrofit().getInstance().updatebarang(id, nama, jumlah, img)
            .enqueue(object : Callback<ResponseStatus> {
                override fun onResponse(
                    call: Call<ResponseStatus>,
                    response: Response<ResponseStatus>
                ) {
                    val res = response.body()
                    mView.onDatasend(res)
                    mView.onHideloading()
                }

                override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                    mView.onDataeror(t)
                    mView.onHideloading()
                }

            })
    }
    fun deleteItem(id : String){
        mView.onShowloading()
        Initretrofit().getInstance().deleteitem(id)
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