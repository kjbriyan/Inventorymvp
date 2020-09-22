package com.arsitek.inventorymvp.Login

import android.util.Log
import com.arsitek.inventorymvp.Activity.AuthActivity
import com.arsitek.inventorymvp.Activity.HomeActivity
import com.arsitek.inventorymvp.Model.ResponseLogin
import com.arsitek.inventorymvp.network.RepositoryCallback
import com.arsitek.inventorymvp.network.RetrofitRepository
import com.example.myinventory.util.Helper
import kotlinx.android.synthetic.main.fragment_auth.*

class LoginPresenter(
    private val mView: RepositoryCallback<ResponseLogin>,
    private val retrofit: RetrofitRepository
) {
    fun login(uname: String, pass: String) {
        retrofit.getDataLogin(uname, pass, object : RepositoryCallback<ResponseLogin> {
            override fun onDataLoaded(data: List<ResponseLogin>?) {
                Helper().debuger(data?.get(0)?.status.toString())
                Log.d("authf", "onDa"+data?.get(0)?.status.toString())
                mView.onDataLoaded(data)
            }
            override fun onDataError() {
                mView.onDataError()
            }
        })
    }
}