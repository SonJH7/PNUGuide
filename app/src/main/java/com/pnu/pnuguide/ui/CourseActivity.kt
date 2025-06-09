package com.pnu.pnuguide.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R

class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_course)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_course)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
    }
}
