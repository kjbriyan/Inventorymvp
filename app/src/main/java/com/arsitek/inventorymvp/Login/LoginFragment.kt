package com.arsitek.inventorymvp.Login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arsitek.inventorymvp.Activity.HomeActivity
import com.arsitek.inventorymvp.Model.ResponseLogin
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.network.RepositoryCallback
import com.arsitek.inventorymvp.network.RetrofitRepository
import com.example.myinventory.util.Helper
import kotlinx.android.synthetic.main.fragment_auth.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthFragment : Fragment(), RepositoryCallback<ResponseLogin> {
    // TODO: Rename and change types of parameters
    private val mPresenter by lazy { LoginPresenter(this, RetrofitRepository(requireContext())) }
    lateinit var pb : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        pb = view.findViewById(R.id.pb_login)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var uname = et_username.text
        var pass = et_pw.text
        btn_login.setOnClickListener {
            pb.visibility = View.VISIBLE
            Toast.makeText(context, uname, Toast.LENGTH_SHORT).show()
            mPresenter.login(uname.toString(), pass.toString())
        }
    }

//    override fun onShowLoading() {
//        activity.let {
//            pb_login?.visibility = View.VISIBLE
//        }
//
////        pb.visibility =View.VISIBLE
//        Log.d("authf", "onShow")
//    }
//
//    override fun onHideLoading() {
//        activity?.pb_login?.visibility = View.GONE
//        pb_login?.visibility = View.GONE
//        pb.visibility = View.GONE
//        Log.d("authf", "onHideloading")
//    }

    override fun onDataLoaded(data: List<ResponseLogin>?) {
        Log.d("authf", "onDa"+data?.get(0)?.status.toString())
        pb.visibility = View.GONE
        activity.let {
            Log.d("authf", "onDa"+data?.get(0)?.status.toString())
        }
    }

    override fun onDataError() {
        activity.let {
            Helper().debuger("data eror")
        }
        pb.visibility = View.GONE
        Toast.makeText(context, "404", Toast.LENGTH_SHORT).show()
    }

}