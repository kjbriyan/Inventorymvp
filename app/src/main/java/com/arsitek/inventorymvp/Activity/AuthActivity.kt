package com.arsitek.inventorymvp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arsitek.inventorymvp.Login.AuthFragment
import com.arsitek.inventorymvp.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager!!.
        beginTransaction().
        add(R.id.fragment_nav_host_auth,AuthFragment(),"Fragment Auth").commit()
    }


}