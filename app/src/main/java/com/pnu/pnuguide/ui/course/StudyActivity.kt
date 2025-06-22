package com.pnu.pnuguide.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.setupHeader2

class StudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_study)
        toolbar.setupHeader2(this, R.string.title_study)
        toolbar.setNavigationOnClickListener { finish() }

        val recycler = findViewById<RecyclerView>(R.id.recycler_course)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = CourseListAdapter()
        recycler.adapter = adapter
        adapter.onItemClick = { item ->
            SpotDetailDialogFragment.newInstance(item)
                .show(supportFragmentManager, "detail")
        }

        val items = listOf(
            CourseItem(
                "Library Tour",
                "Approx. 1 hour",
                "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/z7s2j8n3_expires_30_days.png"
            ),
            CourseItem(
                "Quiet Cafes",
                "Approx. 2 hours",
                "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/hc760uhy_expires_30_days.png"
            ),
            CourseItem(
                "Rest Areas",
                "Approx. 45 min",
                "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/yb4j87iw_expires_30_days.png"
            )
        )
        adapter.submitList(items)
    }
}
