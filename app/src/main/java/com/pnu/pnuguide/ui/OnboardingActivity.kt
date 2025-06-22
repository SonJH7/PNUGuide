package com.pnu.pnuguide.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.theme.PNUGuideTheme
import com.pnu.pnuguide.MainActivity
import com.pnu.pnuguide.data.AuthRepository
import com.pnu.pnuguide.ui.LoginActivity

@Composable
fun OnboardingScreen(onStart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onStart) {
                Text(text = "Get Started")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

class OnboardingActivity : ComponentActivity() {
    private val handler = Handler(Looper.getMainLooper())

    private val navigateRunnable = Runnable {
        val dest = if (AuthRepository.currentUser() != null) MainActivity::class.java else LoginActivity::class.java
        startActivity(Intent(this, dest))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PNUGuideTheme {
                OnboardingScreen(onStart = {
                    handler.removeCallbacks(navigateRunnable)
                    navigateRunnable.run()
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (AuthRepository.currentUser() != null) {
            navigateRunnable.run()
        } else {
            handler.postDelayed(navigateRunnable, 2000)
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(navigateRunnable)
    }
}
