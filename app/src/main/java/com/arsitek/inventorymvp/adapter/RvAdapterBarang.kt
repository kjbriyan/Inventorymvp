package com.arsitek.inventorymvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.model.DataItemBarang
import com.example.myinventory.network.Initretrofit
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
            itemView.ln_barang.setOnClickListener {

            }
            Picasso.get().load(Initretrofit().NAMA_DOMAIN + "img/" + get?.img)
                .into(itemView.iv_barang)

        }
    }
}
