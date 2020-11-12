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
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.util.Helper
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pixplicity.easyprefs.library.Prefs


class HomeFragment : Fragment(), HomeView {
    lateinit var recyclerView: RecyclerView
    lateinit var mAdView: AdView

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
            Helper().moveActivity(context, AddItemActivity::class.java)
        }
        val level = Prefs.getString(SharedPrefs.level, "")
        if (level == "2") {
            view.findViewById<FloatingActionButton>(R.id.btn_add).visibility = View.GONE
        }

        super.onActivityCreated(savedInstanceState)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adView = AdView(requireContext())
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-8610166076120392/2647186398"
        MobileAds.initialize(requireContext()) {

            mAdView = view.findViewById(R.id.ads_banner)
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
        }
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
        }
    }
    override fun onResume() {
        super.onResume()

    }


}