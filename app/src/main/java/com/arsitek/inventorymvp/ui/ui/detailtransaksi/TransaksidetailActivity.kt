package com.arsitek.inventorymvp.ui.ui.detailtransaksi

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.model.ResponseStatus
import com.example.myinventory.network.Initretrofit
import com.squareup.picasso.Picasso
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_transaksidetail.*

class TransaksidetailActivity : AppCompatActivity(), TdetailView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksidetail)
        val status = intent.getStringExtra("status")
        val namab = intent.getStringExtra("namaBarang")
        val nama = intent.getStringExtra("nama")
        val pinjam = intent.getStringExtra("pinjam")
        val img = intent.getStringExtra("img")

        tv_nBarang.setText(namab)
        tv_dipinjam.setText("Meminjam : " + pinjam + " Unit")
        tv_namapeminjam.setText(nama)
        Picasso.get().load(Initretrofit().IMAGE+img).into(iv_barang)

        if (status == "1") {
            tv_status.text = "Di pinjam"
        } else if (status == "0") {
            tv_status.text = "Pending"
        } else if (status == "2") {
            tv_status.text = "Di kembalikan"
        } else {
            tv_status.text = "Di Tolak"
        }
        view()
    }

    fun view() {
        val idtrans = intent.getStringExtra("idtrans")
        val idbar = intent.getStringExtra("idbar")
        val status = intent.getStringExtra("status")
        val stockdb = intent.getStringExtra("stockdb")
        val pinjam = intent.getStringExtra("pinjam")
        var stockmin = stockdb?.toInt()?.minus(pinjam!!.toInt())
        var stockplus = stockdb?.toInt()?.plus(pinjam!!.toInt())
        Log.d("stocking","min "+stockmin+"plus "+stockplus)

        val presenter = TdetailPresenter(this)
        when(status){
            "1" ->{
                ln_btn.visibility = View.GONE
                iv_delete.visibility = View.GONE
            }
            "2"->{
                ln_btn.visibility = View.GONE
                btn_acc.visibility = View.GONE
            }
            "3"->{
                ln_btn.visibility = View.GONE
                btn_acc.visibility = View.GONE
            }
            else-> {
                btn_acc.visibility = View.GONE
                iv_delete.visibility = View.GONE
            }
        }
        btn_acc.setOnClickListener {
            presenter.updatestatus(idtrans.toString(), "2")
            presenter.retur(idbar.toString(),stockplus.toString())
        }
        btn_terima.setOnClickListener {
            presenter.updatestatus(idtrans.toString(), "1")
            presenter.retur(idbar.toString(),stockmin.toString())
        }
        btn_tolak.setOnClickListener {
            presenter.updatestatus(idtrans.toString(), "3")
        }
        iv_delete.setOnClickListener {
            val dialogitem =
                arrayOf<CharSequence>("Yes", "No")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("DELETE ?")
            builder.setItems(dialogitem, DialogInterface.OnClickListener { dialogInterface, i ->
                when (i) {
                    0 -> presenter.delete(idtrans.toString())
                    1 -> Toasty.normal(this,"Cancel Delete",Toast.LENGTH_SHORT).show()
                }
            })
            builder.create().show()
        }
    }

    override fun onLoading() {
        pb_tdetail.visibility = View.VISIBLE
    }

    override fun onHide() {
        pb_tdetail.visibility = View.GONE
    }

    override fun onSenddata(t: ResponseStatus?) {
        Toasty.success(this, "sukses", Toast.LENGTH_SHORT).show()
        ln_btn.visibility = View.GONE
    }

    override fun onEror(t: Throwable) {
        Toasty.error(this, t.message.toString(), Toast.LENGTH_SHORT).show()
    }
}