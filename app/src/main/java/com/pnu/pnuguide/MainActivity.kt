package com.pnu.pnuguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnu.pnuguide.ui.home.HomeActivity
import com.pnu.pnuguide.ui.map.MapActivity
import com.pnu.pnuguide.ui.profile.ProfileActivity
import com.pnu.pnuguide.ui.course.CourseActivity
import com.pnu.pnuguide.ui.stamp.StampActivity
import com.pnu.pnuguide.ui.chat.ChatActivity
import android.content.Intent
import com.pnu.pnuguide.ui.SettingsActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_settings) {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            } else {
                false
            }
        }
        
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.nav_map -> startActivity(Intent(this, MapActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, ProfileActivity::class.java))
            }
            true
        }
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_home
        }

        findViewById<MaterialButton>(R.id.btn_course).setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.btn_stamp).setOnClickListener {
            startActivity(Intent(this, StampActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.btn_chat).setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }

}