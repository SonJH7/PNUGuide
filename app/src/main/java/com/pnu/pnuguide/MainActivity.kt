package com.pnu.pnuguide

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.commit
import com.pnu.pnuguide.ui.chat.ChatFragment
import com.pnu.pnuguide.ui.course.CourseFragment
import com.pnu.pnuguide.ui.stamp.StampFragment

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var drawerToggle: ActionBarDrawerToggle

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        findViewById<NavigationView>(R.id.navigation_view).setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_settings -> startActivity(android.content.Intent(this, com.pnu.pnuguide.ui.SettingsActivity::class.java))
                R.id.menu_reset_nickname -> {/* TODO */}
            }
            drawerLayout.closeDrawers()
            true
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

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}