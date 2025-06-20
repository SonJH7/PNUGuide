package com.pnu.pnuguide.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import androidx.core.content.ContextCompat
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.map.MapActivity

fun MaterialToolbar.setupHeader1(activity: AppCompatActivity, titleResId: Int) {
    activity.setSupportActionBar(this)
    this.title = activity.getString(titleResId)
    this.navigationIcon = ContextCompat.getDrawable(activity, R.drawable.ic_map)
    this.setNavigationOnClickListener {
        activity.startActivity(Intent(activity, MapActivity::class.java))
    }
    this.inflateMenu(R.menu.menu_home_toolbar)
    this.setOnMenuItemClickListener { item ->
        if (item.itemId == R.id.action_settings) {
            activity.startActivity(Intent(activity, SettingsActivity::class.java))
            true
        } else {
            false
        }
    }
}

fun MaterialToolbar.setupHeader2(activity: AppCompatActivity, titleResId: Int) {
    activity.setSupportActionBar(this)
    this.title = activity.getString(titleResId)
    this.navigationIcon = ContextCompat.getDrawable(activity, R.drawable.ic_arrow_back_black_24)
    this.setNavigationOnClickListener { activity.finish() }
}
