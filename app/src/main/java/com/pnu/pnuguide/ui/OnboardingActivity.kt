package com.pnu.pnuguide.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pnu.pnuguide.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val handler = Handler(Looper.getMainLooper())

    private val navigateRunnable = Runnable {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetStarted.setOnClickListener {
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
