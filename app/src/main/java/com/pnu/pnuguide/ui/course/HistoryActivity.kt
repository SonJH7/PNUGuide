package com.pnu.pnuguide.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.setupHeader2

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_history)
        toolbar.setupHeader2(this, R.string.title_history)
    }
}
