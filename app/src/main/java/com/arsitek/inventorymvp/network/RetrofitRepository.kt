package com.arsitek.inventorymvp.network

import android.content.Context
import android.util.Log
import com.arsitek.inventorymvp.model.ResponseLogin
import com.arsitek.inventorymvp.ui.HomeActivity
import com.example.myinventory.network.Initretrofit
import com.example.myinventory.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository(private val context: Context) {
    companion object {
        private val TAG = "Retrofit"
    }

    fun getDataLogin(
        username: String,
        password: String,
        callback: RepositoryCallback<ResponseLogin>
    ) {
        val init =
            Initretrofit().getInstance().userLogin(username, password)
        Log.d(TAG, username)
        init.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val res = response.body()
                Log.d(TAG, res?.status.toString())
                if (res?.status.toString() == "200") {
                    var pas = res?.data?.get(0)?.password.toString()
                    var em = res?.data?.get(0)?.name.toString()
                    Log.d(TAG, "suk")
                    if (pas == password && em == username)
                        Helper().moveActivity(context, HomeActivity::class.java)
                    else
                        Log.d(TAG, "username and pass not match")
                } else {
                    Log.d(TAG, "fail network")
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d(TAG, "failure " + t.message)
            }

        })

    }
}