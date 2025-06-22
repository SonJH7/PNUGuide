package com.pnu.pnuguide.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.setupHeader2
import android.widget.TextView
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_settings)
        toolbar.setupHeader2(this, R.string.settings)

        val user = FirebaseAuth.getInstance().currentUser
        val nameView = findViewById<TextView>(R.id.tv_settings_name)
        val emailView = findViewById<TextView>(R.id.tv_settings_email)
        val email = user?.email
        val prefix = email?.substringBefore("@") ?: ""
        nameView.text = prefix
        emailView.text = email ?: ""


        setItem(R.id.item_account, R.drawable.ic_arrow_back_black_24, "Account Settings")
        setItem(R.id.item_info, R.drawable.ic_arrow_back_black_24, "App Information")
    }

    private fun setItem(resId: Int, iconRes: Int, title: String) {
        val view = findViewById<android.view.View>(resId)
        view.findViewById<ImageView>(R.id.icon).setImageResource(iconRes)
        view.findViewById<TextView>(R.id.title).text = title
    }
}