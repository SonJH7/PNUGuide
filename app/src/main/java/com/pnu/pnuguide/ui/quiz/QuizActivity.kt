package com.pnu.pnuguide.ui.quiz

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.pnu.pnuguide.R
import com.pnu.pnuguide.data.StampProgress
import com.pnu.pnuguide.ui.SettingsActivity

open class QuizActivity : AppCompatActivity() {

    open val question: String = ""
    open val answer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_quiz)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu_home_toolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(android.content.Intent(this, com.pnu.pnuguide.ui.map.MapActivity::class.java))
        }
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_settings) {
                startActivity(android.content.Intent(this, SettingsActivity::class.java))
                true
            } else {
                false
            }
        }

        findViewById<TextView>(R.id.text_question).text = question

        findViewById<MaterialButton>(R.id.button_o).setOnClickListener {
            handleAnswer(true, answer)
        }
        findViewById<MaterialButton>(R.id.button_x).setOnClickListener {
            handleAnswer(false, answer)
        }
    }

    private fun handleAnswer(userAnswer: Boolean, correctAnswer: Boolean) {
        if (userAnswer == correctAnswer) {
            StampProgress.increment(this)
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show()
        }
        finish()
    }

}
