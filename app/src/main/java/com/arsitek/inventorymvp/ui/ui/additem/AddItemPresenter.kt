package com.arsitek.inventorymvp.ui.ui.additem

import com.arsitek.inventorymvp.model.ResponseStatus
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddItemPresenter(private val mView : AddItemView) {
    private val TAG ="AddItem"

    fun addItem(nama : String, jumlah : String, img : String?){
        mView.onShowloading()
        Initretrofit().getInstance().insertbarang(nama,jumlah,img)
            .enqueue(object : Callback<ResponseStatus>{
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

}