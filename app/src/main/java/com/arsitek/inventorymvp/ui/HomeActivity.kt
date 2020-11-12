package com.arsitek.inventorymvp.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.arsitek.inventorymvp.R
import com.arsitek.inventorymvp.ui.ui.login.LoginActivity
import com.example.myinventory.util.Helper
import com.pixplicity.easyprefs.library.Prefs
import es.dmoral.toasty.Toasty

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_logout->
            {
                logout()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun logout(){
        val dialogitem =
            arrayOf<CharSequence>("Yes", "No")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout ?")
        builder.setItems(dialogitem, DialogInterface.OnClickListener { dialogInterface, i ->
            when (i) {
                0 -> actlogout()
                1 -> Toasty.normal(this,"Cancel", Toast.LENGTH_SHORT).show()
            }
        })
        builder.create().show()
    }

    fun actlogout(){
        Prefs.clear()
        Helper().moveActivity(this,LoginActivity::class.java)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}