package com.arsitek.inventorymvp.ui.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.adapter.RvAdapterBarang
import com.arsitek.inventorymvp.model.DataItemBarang
import com.arsitek.inventorymvp.ui.ui.additem.AddItemActivity
import com.example.myinventory.util.Helper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), HomeView {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.rv_home)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val presenter = HomePresenter(this)
        activity.let {
            presenter.getdata()
        }

        view.findViewById<FloatingActionButton>(R.id.btn_add).setOnClickListener {
            Helper().moveActivity(context,AddItemActivity::class.java)
        }

        super.onActivityCreated(savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onShowLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_home)?.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_home)?.visibility = View.GONE
    }

    override fun onDataloaded(results: List<DataItemBarang?>) {
        if (results.isNotEmpty()) {
            activity.let {
                with(recyclerView) {
                    adapter = RvAdapterBarang(results)
                }
            }
        }else{
            Log.d("HomeFragment","null data")
        }
    }

    override fun onDataeror(message: Throwable) {
        Toast.makeText(activity,message.cause.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val presenter = HomePresenter(this)
        activity.let {
            presenter.getdata()
        }
    }


}