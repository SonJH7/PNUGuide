package com.pnu.pnuguide.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.setupHeader1

class CourseActivity : AppCompatActivity() {
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_course)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_course)
        toolbar.setupHeader1(this, R.string.title_course)

        Glide.with(this).load("https://i.imgur.com/1tMFzp8.png")
            .into(findViewById(R.id.rs49bgap0hwg))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/z7s2j8n3_expires_30_days.png")
            .into(findViewById(R.id.rl432c43q9nj))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/hc760uhy_expires_30_days.png")
            .into(findViewById(R.id.rd7w45soo08))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/yb4j87iw_expires_30_days.png")
            .into(findViewById(R.id.rzafkzrm2ak))

        val searchEdit: EditText = findViewById(R.id.r9hg358v9thk)
        searchEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchQuery = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
