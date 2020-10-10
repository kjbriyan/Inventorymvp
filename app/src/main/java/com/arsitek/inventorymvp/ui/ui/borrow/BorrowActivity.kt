package com.arsitek.inventorymvp.ui.ui.borrow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arsitek.inventorymvp.R
import com.example.myinventory.network.Initretrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_borrow.*


class BorrowFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nama = intent?.getStringExtra("nama")
        val img = intent.getStringExtra("img")
        val stock = intent?.getStringExtra("stock")
        Log.d("gera", "aa " + img.toString())
        tv_borrow.text = nama
        et_borrow.setText(stock)
        val iv = findViewById<ImageView>(R.id.iv_borrow)
        Picasso.get().load(Initretrofit().IMAGE+img).into(iv)
    }





}