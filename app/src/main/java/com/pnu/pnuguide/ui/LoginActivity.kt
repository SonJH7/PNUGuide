package com.pnu.pnuguide.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.pnu.pnuguide.MainActivity
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<MaterialButton>(R.id.button_login).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<MaterialButton>(R.id.button_signup).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
