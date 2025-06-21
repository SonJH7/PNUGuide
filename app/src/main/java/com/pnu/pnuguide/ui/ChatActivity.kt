package com.pnu.pnuguide.ui.chat

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnu.pnuguide.R
import com.pnu.pnuguide.MainActivity
import com.pnu.pnuguide.ui.course.CourseActivity
import com.pnu.pnuguide.ui.stamp.StampActivity
import com.pnu.pnuguide.ui.setupHeader1

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_chat)
        toolbar.setupHeader1(this, R.string.title_chatbot)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_course -> {
                    startActivity(Intent(this, CourseActivity::class.java))
                    true
                }
                R.id.nav_stamp -> {
                    startActivity(Intent(this, StampActivity::class.java))
                    true
                }
                R.id.nav_chat -> true
                else -> false
            }
        }
        bottomNav.menu.findItem(R.id.nav_chat).isChecked = true
    }
}
