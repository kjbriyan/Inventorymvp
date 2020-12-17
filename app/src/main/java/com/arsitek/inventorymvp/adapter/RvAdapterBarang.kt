package com.arsitek.inventorymvp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.model.DataItemBarang
import com.arsitek.inventorymvp.ui.ui.borrow.BorrowActivity
import com.arsitek.inventorymvp.ui.ui.update.UpdateActivity
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.network.Initretrofit
import com.pixplicity.easyprefs.library.Prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_barang.view.*


class RvAdapterBarang(var data: MutableList<DataItemBarang>) :
    RecyclerView.Adapter<RvAdapterBarang.MyHolder>(), Filterable {
    internal var list: MutableList<DataItemBarang>

    init {
        this.list = ArrayList(data)
    }


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
            itemView.tv_stock.text = "stock " + get?.jumlahBarang

            val level = Prefs.getString(SharedPrefs.level, "").toString()
            itemView.ln_barang.setOnClickListener {

                if (level == "1") {
                    val i = Intent(itemView.context, UpdateActivity::class.java)
                    i.putExtra("id", get?.idBarang)
                    i.putExtra("nama", get?.namaBarang)
                    i.putExtra("img", get?.img)
                    i.putExtra("stock", get?.jumlahBarang)
                    itemView.context.startActivity(i)
                } else {
                    val i = Intent(itemView.context, BorrowActivity::class.java)
                    i.putExtra("id", get?.idBarang)
                    i.putExtra("nama", get?.namaBarang)
                    i.putExtra("img", get?.img)
                    i.putExtra("stock", get?.jumlahBarang)
                    itemView.context.startActivity(i)
                }
            }
            Picasso.get().load(Initretrofit().NAMA_DOMAIN + "img/" + get?.img)
                .into(itemView.iv_barang)

        }
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterList: MutableList<DataItemBarang> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filterList.addAll(list)
                Log.d("Allitemmmmmmm", "performFiltering: " + list.toString())
            } else {
                val fillPatren = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (items in list) {
                    if (items.namaBarang.toString().toLowerCase().contains(fillPatren)) {
                        filterList.add(items)
                        Log.d("rvadapter", "nut" + filterList.size)
                    }
                }
            }
            val results = FilterResults()
            results.values = filterList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
//            data.dda.addAll(results.values as List<*>)
            with(data) {
                clear()
                addAll(results.values as MutableList<DataItemBarang>)
            }
            notifyDataSetChanged()
        }
    }


}
