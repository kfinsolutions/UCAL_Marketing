package com.ucal.marketing.expo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_home -> Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_contact -> Toast.makeText(applicationContext, "Contact Clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_export_csv -> Toast.makeText(applicationContext, "Export to CSV Clicked", Toast.LENGTH_SHORT).show()
            }

            true

        }

        val mainWeb = findViewById<WebView>(R.id.mainWebView)

        mainWeb.webViewClient = WebViewClient()

        mainWeb.apply {
            loadUrl("file:///android_asset/index.html")
            settings.javaScriptEnabled = true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return true
    }

}