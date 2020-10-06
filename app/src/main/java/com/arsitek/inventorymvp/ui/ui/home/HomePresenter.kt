package com.arsitek.inventorymvp.ui.ui.home

import android.widget.Toast
import com.arsitek.inventorymvp.adapter.RvAdapterBarang
import com.arsitek.inventorymvp.model.DataItemBarang
import com.arsitek.inventorymvp.model.ResponseBarang
import com.example.myinventory.network.Initretrofit
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val view: HomeView) {

    fun getdata() {
        view.onShowLoading()
        Initretrofit().getInstance().getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                view.onDataloaded(response.body()?.data ?: emptyList())
                view.onHideLoading()
            }

            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                view.onHideLoading()
                view.onDataeror(t)
            }

        })
    }
}