package com.arsitek.inventorymvp.ui.ui.borrow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.model.ResponseStatus
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.network.Initretrofit
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_borrow.*


class BorrowFragment : AppCompatActivity(), BorrowView {
    private val TAG = "Borrow"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_borrow)
        val nama = intent?.getStringExtra("nama")
        val img = intent?.getStringExtra("img")
        val stock = intent?.getStringExtra("stock")
        tv_borrow.setText(nama)
        et_borrow.setHint(stock)
        val iv = findViewById<ImageView>(R.id.iv_borrow)
        Picasso.get().load(Initretrofit().IMAGE + img).into(iv)
        view()
    }

    fun view() {
        var stock = intent?.getStringExtra("stock")
        val presenter = BorrowPresenter(this)
        val idbarang = intent.getStringExtra("id").toString()
        val iduser = Prefs.getString(SharedPrefs.idUser, "")
        var stokk: Int = 0
        stokk = stock!!.toInt()
        btn_borrow.setOnClickListener {
            Log.d(TAG, " sianu " + stock.toString() + "nyileh" + et_borrow.text.toString().toInt())

            if (et_borrow.text.toString().toInt() <= stokk) {
                println("benar")
                presenter.sendTransaksi(
                    idbarang,
                    iduser,
                    et_keterangan.text.toString(),
                    et_borrow.text.toString()
                )
            } else {
                Toasty.error(this, "Stock yang di pinjam melebihi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onShowloading() {
        pb_borrow.visibility = View.VISIBLE
    }

    override fun onHideloading() {
        pb_borrow.visibility = View.GONE
    }

    override fun onDatasend(s: ResponseStatus?) {
        Toasty.success(this, "Sukses mengajukan pinjaman", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDataeror(t: Throwable) {
        Toasty.error(this, t.cause.toString(), Toast.LENGTH_SHORT).show()
    }


}