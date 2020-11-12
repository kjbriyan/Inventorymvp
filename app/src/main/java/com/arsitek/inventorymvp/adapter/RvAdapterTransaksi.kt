package com.arsitek.inventorymvp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.model.DataItemTransaksi
import com.arsitek.inventorymvp.ui.ui.detailtransaksi.TransaksidetailActivity
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.network.Initretrofit
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_transaksi.view.*


class RvAdapterTransaksi(var data: List<DataItemTransaksi?>) :
    RecyclerView.Adapter<RvAdapterTransaksi.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapterTransaksi.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_transaksi, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: RvAdapterTransaksi.MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: DataItemTransaksi?) {
            itemView.tv_barang.text = get?.namaBarang
            itemView.tv_stock.text = "Meminjam :" + get?.pinjam + " Unit"
            Picasso.get().load(Initretrofit().IMAGE+get?.img).into(itemView.iv_barang)
            itemView.tv_peminjam.text = get?.nama

            itemView.tv_in.text = "in "+get?.createdAt
            itemView.tv_out.text = "out "+get?.updatedAt

            if (get?.status == "1") {
                itemView.tv_status.text = "Di pinjam"
            } else if (get?.status == "0") {
                itemView.tv_status.text = "Pending"
            } else if (get?.status == "2") {
                itemView.tv_status.text = "Di kembalikan"
            }else {
                itemView.tv_status.text = "Di Tolak"
            }
            val level = Prefs.getString(SharedPrefs.level, "").toString()

            if(level == "1") {
                itemView.ln_barang.setOnClickListener {

                    val i = Intent(itemView.context, TransaksidetailActivity::class.java)
                    i.putExtra("status", get?.status)
                    i.putExtra("namaBarang", get?.namaBarang)
                    i.putExtra("nama", get?.nama)
                    i.putExtra("pinjam", get?.pinjam)
                    i.putExtra("idtrans",get?.idTransaksi)
                    i.putExtra("img",get?.img)
                    i.putExtra("idbar",get?.idBarang)
                    i.putExtra("stockdb",get?.jumlahBarang)
                    itemView.context.startActivity(i)

                }
            }


        }
    }
}
