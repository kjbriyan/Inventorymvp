package com.arsitek.inventorymvp.ui.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.adapter.RvAdapterTransaksi
import com.arsitek.inventorymvp.model.DataItemTransaksi
import es.dmoral.toasty.Toasty


class HistoryFragment : Fragment(), HistoryView {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = root.findViewById(R.id.rv_histori)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = view?.findViewById<Spinner>(R.id.sp_history)
        val bulan = arrayOf(
            "Bulan", "Januari", "February", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
            "September", "Oktober", "November", "Desember"
        )
        val presenter = HistoryPresenter(this)
        val spinnerArrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bulan)
        // The drop down view
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp.adapter = spinnerArrayAdapter
        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.filter(p2.toString())

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toasty.normal(requireContext(), "select item", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onShow() {
        view?.findViewById<ProgressBar>(R.id.pb_transaksi)?.visibility = View.VISIBLE
    }

    override fun onHide() {
        view?.findViewById<ProgressBar>(R.id.pb_transaksi)?.visibility = View.GONE
    }

    override fun onDatashow(t: List<DataItemTransaksi?>) {
        if (t.isNotEmpty()) {
            activity.let {
                with(recyclerView) {
                    adapter = RvAdapterTransaksi(t)
                }
            }
        } else {
            Toasty.error(requireContext(), "null data on this month",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDataeror(t: Throwable) {
        Toasty.error(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
    }
}