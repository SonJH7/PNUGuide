package com.pnu.pnuguide.ui.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.setupHeader1
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.pnu.pnuguide.data.QuizProgress

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutRes = intent.getIntExtra(EXTRA_LAYOUT_RES, R.layout.activity_quiz)
        setContentView(layoutRes)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_quiz)
        val title = intent.getStringExtra(EXTRA_TITLE)
        if (title != null) {
            toolbar.setupHeader1(this, title)
        } else {
            toolbar.setupHeader1(this, R.string.title_quiz)
        }
        toolbar.setNavigationOnClickListener { finish() }

        if (layoutRes == R.layout.activity_quiz_1) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnO.setOnClickListener {
                QuizProgress.markSolved(this, "quiz1")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnX.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_2) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnX.setOnClickListener {
                QuizProgress.markSolved(this, "quiz2")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnO.setOnClickListener {
                Toast.makeText(this, "오답입니다. 1990년대 후반부터 조성되어 2000년대 초반에 주요 조형물들이 설치됨.", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_3) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnX.setOnClickListener {
                QuizProgress.markSolved(this, "quiz3")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnO.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_4) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnO.setOnClickListener {
                QuizProgress.markSolved(this, "quiz4")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnX.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_5) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnX.setOnClickListener {
                QuizProgress.markSolved(this, "quiz5")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnO.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_6) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnX.setOnClickListener {
                QuizProgress.markSolved(this, "quiz6")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnO.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_7) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnO.setOnClickListener {
                QuizProgress.markSolved(this, "quiz7")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnX.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_8) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnO.setOnClickListener {
                QuizProgress.markSolved(this, "quiz8")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnX.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        } else if (layoutRes == R.layout.activity_quiz_9) {
            val btnO = findViewById<MaterialButton>(R.id.btn_option_o)
            val btnX = findViewById<MaterialButton>(R.id.btn_option_x)
            btnX.setOnClickListener {
                QuizProgress.markSolved(this, "quiz9")
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
            btnO.setOnClickListener {
                Toast.makeText(this, "오답입니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home_toolbar, menu)
        menu.findItem(R.id.action_settings)?.apply {
            icon = ContextCompat.getDrawable(this@QuizActivity, R.drawable.ic_settings)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        return true
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_LAYOUT_RES = "extra_layout_res"

        fun start(context: Context, title: String) {
            val layoutRes = when (title) {
                "대학본부" -> R.layout.activity_quiz_1
                "조각공원" -> R.layout.activity_quiz_2
                "새벽벌도서관" -> R.layout.activity_quiz_3
                "예원정" -> R.layout.activity_quiz_4
                "금정회관 식당" -> R.layout.activity_quiz_5
                "경영관" -> R.layout.activity_quiz_6
                "법학관 모의 법정" -> R.layout.activity_quiz_7
                "중앙도서관" -> R.layout.activity_quiz_8
                "음악관" -> R.layout.activity_quiz_9
                "학생회관 식당" -> R.layout.activity_quiz_10
                "경암체육관" -> R.layout.activity_quiz_11
                "약학관" -> R.layout.activity_quiz_12
                "지질박물관" -> R.layout.activity_quiz_13
                "샛벌회관 식당" -> R.layout.activity_quiz_14
                "박물관" -> R.layout.activity_quiz_15
                "10.16기념관" -> R.layout.activity_quiz_16
                "건설관" -> R.layout.activity_quiz_17
                "기계관 V-SPACE" -> R.layout.activity_quiz_18
                "진리의 뜰" -> R.layout.activity_quiz_19
                "인문관" -> R.layout.activity_quiz_20
                "넉넉한 터" -> R.layout.activity_quiz_21
                "시월 광장" -> R.layout.activity_quiz_22
                "민주 언덕" -> R.layout.activity_quiz_23
                "미리내 골" -> R.layout.activity_quiz_24
                else -> R.layout.activity_quiz
            }
            val intent = Intent(context, QuizActivity::class.java)
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_LAYOUT_RES, layoutRes)
            context.startActivity(intent)
        }
    }
}
