package com.arsitek.inventorymvp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.model.DataItem
import com.arsitek.inventorymvp.ui.ui.home.HomeFragment

class RvAdapterBarang(val data: List<DataItem>?, val itemClickListener: HomeFragment) :
    RecyclerView.Adapter<RvAdapterBarang.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapterBarang.MyHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RvAdapterBarang.MyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
