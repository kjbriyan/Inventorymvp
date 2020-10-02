package com.arsitek.inventorymvp.ui.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arsitek.inventorymvp.model.DataItem
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.ui.HomeActivity
import com.arsitek.inventorymvp.util.SharedPrefs
import com.example.myinventory.util.Helper
import com.pixplicity.easyprefs.library.Prefs
import es.dmoral.toasty.Toasty
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
class LoginFragment : Fragment(), LoginView {
    // TODO: Rename and change types of parameters
//    private val mPresenter by lazy { LoginPresenter(this, RetrofitRepository(requireContext())) }
    lateinit var pb: ProgressBar
    lateinit var tv: TextView
    private val TAG = "Fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        pb = view.findViewById(R.id.pb_login)
        tv = view.findViewById(R.id.tv_signup)
        super.onActivityCreated(savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = LoginPresenter(this)
        var uname = et_username.text
        var pass =et_pw.text

        if (Prefs.getString(SharedPrefs.idUser,"") != null){
            Helper().moveActivity(context, HomeActivity::class.java)
            Log.d(TAG,"id "+Prefs.getString(SharedPrefs.idUser,""))
        }else{
            Log.d(TAG,"null")
        }

        btn_login.setOnClickListener {
            presenter.login(uname.toString(),pass.toString())
        }
    }

    override fun onShowLoading() {
        pb.visibility = View.VISIBLE
        Log.d(TAG, "show")
    }

    override fun onHideLoading() {
        pb.visibility = View.GONE
        Log.d(TAG, "hide")
    }

    override fun onDataloaded(results: List<DataItem?>) {
        Log.d(TAG, "data")
        if (results.isNotEmpty()) {
            Helper().moveActivity(context, HomeActivity::class.java)
            Prefs.clear()
            Log.d(TAG, "data "+results.get(0)?.name)
            Prefs.putString(SharedPrefs.idUser,results.get(0)?.idUser)
            Prefs.putString(SharedPrefs.name,results.get(0)?.name)
            Prefs.putString(SharedPrefs.level,results.get(0)?.level)
            Prefs.putString(SharedPrefs.username,results.get(0)?.username)
            Toasty.success(requireContext(), "Sukses", Toast.LENGTH_SHORT).show()
        } else {
            Toasty.error(requireContext(), "user password salah", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDataeror(message: Throwable) {
        Log.d(TAG, "dateror")
        Toasty.error(requireContext(), message.message.toString(), Toast.LENGTH_SHORT).show()
    }



}