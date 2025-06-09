package com.pnu.pnuguide.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R

class SpotListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot_list)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_spot_list)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        // TODO: load spot list
    }
}