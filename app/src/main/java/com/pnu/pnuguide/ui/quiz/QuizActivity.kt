package com.pnu.pnuguide.ui.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.setupHeader1

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_quiz)
        toolbar.setupHeader1(this, R.string.title_quiz)
        toolbar.setNavigationOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
    }
}
