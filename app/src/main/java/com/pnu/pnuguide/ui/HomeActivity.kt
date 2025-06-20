package com.pnu.pnuguide.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.SettingsActivity
import com.pnu.pnuguide.ui.map.MapActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_home)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu_home_toolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_settings) {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            } else {
                false
            }
        }
    }
}
