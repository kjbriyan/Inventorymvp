package com.arsitek.inventorymvp.ui.ui.login

import android.util.Log
import com.arsitek.inventorymvp.model.ResponseLogin
import com.example.myinventory.network.Initretrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val mView: LoginView) {
    private val TAG = "presenter"

    fun login(uname: String, pass: String) {
        mView.onShowLoading()
        val init = Initretrofit().getInstance().userLogin(uname, pass)
        Log.d(TAG, uname)
        init.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val res = response.body()
                mView.onDataloaded(response.body()?.data ?: emptyList())
                mView.onHideLoading()
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d(TAG, "failure " + t.message)
                mView.onHideLoading()
                mView.onDataeror(t)
            }

        })

    }
}