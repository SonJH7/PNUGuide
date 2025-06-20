package com.pnu.pnuguide

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnu.pnuguide.ui.SettingsActivity
import com.pnu.pnuguide.ui.home.HomeFragment
import com.pnu.pnuguide.ui.map.MapFragment
import com.pnu.pnuguide.ui.map.MapActivity
import androidx.core.content.ContextCompat
import com.pnu.pnuguide.ui.profile.ProfileFragment

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
                R.id.nav_home -> {
                    toolbar.title = getString(R.string.title_home)
                    toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_map)
                    toolbar.setNavigationOnClickListener {
                        startActivity(Intent(this, MapActivity::class.java))
                    }
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, HomeFragment())
                    }
                    true
                }
                R.id.nav_map -> {
                    toolbar.title = getString(R.string.title_map)
                    toolbar.navigationIcon = null
                    toolbar.setNavigationOnClickListener(null)
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, MapFragment())
                    }
                    true
                }
                R.id.nav_profile -> {
                    toolbar.title = getString(R.string.title_profile)
                    toolbar.navigationIcon = null
                    toolbar.setNavigationOnClickListener(null)
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, ProfileFragment())
                    }
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_home
        }
    }
}
