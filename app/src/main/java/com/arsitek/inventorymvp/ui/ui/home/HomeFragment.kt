package com.arsitek.inventorymvp.ui.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.adapter.RvAdapterBarang
import com.arsitek.inventorymvp.model.DataItemBarang
import com.arsitek.inventorymvp.ui.ui.additem.AddItemActivity
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.util.Helper
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeView {
    lateinit var recyclerView: RecyclerView
    lateinit var mAdView: AdView
    lateinit var search : EditText
    lateinit var adapterr: RvAdapterBarang

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.rv_home)
        recyclerView.layoutManager = LinearLayoutManager(context)
        search = view.findViewById(R.id.search)
        val presenter = HomePresenter(this)
        activity.let {
            presenter.getdata()
        }

        view.findViewById<FloatingActionButton>(R.id.btn_add).setOnClickListener {
            Helper().moveActivity(context, AddItemActivity::class.java)
        }
        val level = Prefs.getString(SharedPrefs.level, "")
        if (level == "2") {
            view.findViewById<FloatingActionButton>(R.id.btn_add).visibility = View.GONE
        }

        super.onActivityCreated(savedInstanceState)
        return view
    }


    override fun onShowLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_home)?.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        view?.findViewById<ProgressBar>(R.id.pb_home)?.visibility = View.GONE
    }

    override fun onDataloaded(results: MutableList<DataItemBarang>) {
        if (results.isNotEmpty()) {
            adapterr = RvAdapterBarang(results)
            activity.let {
                with(recyclerView) {
                    adapter = adapterr
                    search.addTextChangedListener(object : TextWatcher{
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            adapterr.filter.filter(p0)
                        }

                        override fun afterTextChanged(p0: Editable?) {

                        }

                    })

                }
            }

        } else {
            Log.d("HomeFragment", "null data")
        }


    }

    override fun onDataeror(message: Throwable) {
        Toast.makeText(activity, message.cause.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val presenter = HomePresenter(this)
        activity.let {
            presenter.getdata()
            search.setText("")
        }
    }
    override fun onResume() {
        super.onResume()
    }


}