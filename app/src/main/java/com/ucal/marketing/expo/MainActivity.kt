package com.ucal.marketing.expo

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.frameLayout, HomeFragment())
        fragment.commit()

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_contact -> replaceFragment(ContactFragment(), it.title.toString())
                R.id.nav_export_csv -> replaceFragment(ExportFragment(), it.title.toString())
            }

            true

        }
    }

    override fun onBackPressed() {
        //Menu Handler
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{

            if(supportFragmentManager.backStackEntryCount > 0){
                supportFragmentManager.popBackStack()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Exit Application")
                    .setMessage("Are you sure want to exit?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes") { _, _ ->
                        finish()
                        super.onBackPressed()
                    }.create().show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return true
    }

    //Custom Methods
    private fun replaceFragment(fragment: Fragment, title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment).addToBackStack(null)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }


}