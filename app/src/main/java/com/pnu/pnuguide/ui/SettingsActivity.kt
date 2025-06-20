package com.pnu.pnuguide.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.HeaderUtils.setupHeader2
import android.widget.TextView
import android.widget.ImageView

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_settings)
        toolbar.setupHeader2(this, R.string.settings)

        setItem(R.id.item_account, R.drawable.ic_arrow_back_black_24, "Account Settings")
        setItem(R.id.item_notification, R.drawable.ic_arrow_back_black_24, "Notification Settings")
        setItem(R.id.item_info, R.drawable.ic_arrow_back_black_24, "App Information")
        setItem(R.id.item_logout, R.drawable.ic_arrow_back_black_24, "Log Out")
    }

    private fun setItem(resId: Int, iconRes: Int, title: String) {
        val view = findViewById<android.view.View>(resId)
        view.findViewById<ImageView>(R.id.icon).setImageResource(iconRes)
        view.findViewById<TextView>(R.id.title).text = title
    }
}