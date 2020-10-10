package com.arsitek.inventorymvp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.model.DataItemBarang
import com.arsitek.inventorymvp.ui.ui.borrow.BorrowFragment
import com.arsitek.inventorymvp.ui.ui.update.UpdateActivity
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.network.Initretrofit
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_barang.view.*

class RvAdapterBarang(val data: List<DataItemBarang?>) :
    RecyclerView.Adapter<RvAdapterBarang.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapterBarang.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_barang, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: RvAdapterBarang.MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: DataItemBarang?) {
            itemView.tv_barang.text = get?.namaBarang
            itemView.tv_stock.text = get?.jumlahBarang

            val level = Prefs.getString(SharedPrefs.level, "").toString()
            itemView.ln_barang.setOnClickListener {

                if (level== "1"){
                    val i = Intent(itemView.context,UpdateActivity::class.java)
                    i.putExtra("id",get?.idBarang)
                    i.putExtra("nama",get?.namaBarang)
                    i.putExtra("img",get?.img)
                    i.putExtra("stock",get?.jumlahBarang)
                    itemView.context.startActivity(i)
                }else{
                    val i = Intent(itemView.context,BorrowFragment::class.java)
                    i.putExtra("id",get?.idBarang)
                    i.putExtra("nama",get?.namaBarang)
                    i.putExtra("img",get?.img)
                    i.putExtra("stock",get?.jumlahBarang)
                    itemView.context.startActivity(i)
                }
            }
            Picasso.get().load(Initretrofit().NAMA_DOMAIN + "img/" + get?.img)
                .into(itemView.iv_barang)

        }
    }
}
