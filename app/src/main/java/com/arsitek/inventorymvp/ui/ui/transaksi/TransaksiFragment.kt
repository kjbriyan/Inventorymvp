package com.arsitek.inventorymvp.ui.ui.transaksi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.adapter.RvAdapterBarang
import com.arsitek.inventorymvp.adapter.RvAdapterTransaksi
import com.arsitek.inventorymvp.model.DataItemTransaksi
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_transaksi.*


class TransaksiFragment : Fragment(), TransaksiView {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_transaksi, container, false)
        recyclerView = root.findViewById(R.id.rv_transaksi)
        return root
    }

    override fun onStart() {
        super.onStart()
        val presenter = TransaksiPresenter(this)
        presenter.getData()
    }

    override fun onLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_transaksi)?.visibility = View.VISIBLE
    }

    override fun onHide() {
        view?.findViewById<ProgressBar>(R.id.pb_transaksi)?.visibility = View.INVISIBLE
    }

    override fun onSukses(t: List<DataItemTransaksi?>) {
        if (t.isNotEmpty()) {
            activity.let {
                with(recyclerView) {
                    adapter = RvAdapterTransaksi(t)
                }
            }
        }else{
            Log.d("Transaksi Fragment","null data")
        }
    }

    override fun onEror(t: Throwable) {
        Toasty.error(requireContext(),t.toString(),Toast.LENGTH_SHORT).show()
    }

}