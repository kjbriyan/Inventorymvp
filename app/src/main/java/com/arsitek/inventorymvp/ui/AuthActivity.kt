package com.arsitek.inventorymvp.ui

//import com.arsitek.inventorymvp.Login.LoginFragment
//import com.arsitek.inventorymvp.Login.AuthFragment

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.arsitek.inventorymvp.ui.ui.login.LoginFragment
import com.arsitek.inventorymvp.R

class AuthActivity : AppCompatActivity() {
    private val TAG = "Retrofit"
    private lateinit var pb: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager!!.beginTransaction()
            .add(R.id.fragment_nav_host_auth, LoginFragment(), "Fragment Auth").commit()

//        pb = findViewById(R.id.pb_login)
//        val presenter = LoginPresenter(this@AuthActivity)
//        var uname = findViewById<TextInputEditText>(R.id.et_username).text
//        var pass = findViewById<TextInputEditText>(R.id.et_pw).text
//        btn_login.setOnClickListener {
//            presenter.login(uname.toString(), pass.toString())
//        }
    }

//    override fun onShowLoading() {
//        pb.visibility = View.VISIBLE
//        Log.d(TAG, "show")
//    }
//
//    override fun onHideLoading() {
//        pb.visibility = View.GONE
//        Log.d(TAG, "hide")
//    }
//
//    override fun onDataloaded(results: List<DataItem?>) {
//        Log.d(TAG, "data")
//
//        if (results.isNotEmpty()) {
//            Helper().moveActivity(this@AuthActivity, HomeActivity::class.java)
//            Toasty.success(this@AuthActivity, "Sukses", Toast.LENGTH_SHORT).show()
//        } else {
//            Toasty.error(this@AuthActivity, "user password salah", Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//    override fun onDataeror(message: Throwable) {
//        Log.d(TAG, "dateror")
////        Toasty.error(this@AuthActivity, message, Toast.LENGTH_SHORT).show()
//    }
}