package com.pnu.pnuguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.commit
import com.pnu.pnuguide.ui.chat.ChatFragment
import com.pnu.pnuguide.ui.course.CourseFragment
import com.pnu.pnuguide.ui.stamp.StampFragment
import android.content.Intent
import com.pnu.pnuguide.ui.SettingsActivity

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
                R.id.nav_course -> switchFragment(CourseFragment())
                R.id.nav_stamp -> switchFragment(StampFragment())
                R.id.nav_chat -> switchFragment(ChatFragment())
            }
            true
        }
                if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_course
        }
    }

            private fun switchFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
        }
    }

}