package com.pnu.pnuguide.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pnu.pnuguide.R
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())

    private val navigateRunnable = Runnable {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        findViewById<MaterialButton>(R.id.button_get_started).setOnClickListener {
            handler.removeCallbacks(navigateRunnable)
            navigateRunnable.run()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(navigateRunnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(navigateRunnable)
    }
}
