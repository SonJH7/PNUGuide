package com.pnu.pnuguide.ui.stamp

import android.os.Bundle
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import com.google.android.material.button.MaterialButton
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnu.pnuguide.R
import com.pnu.pnuguide.MainActivity
import com.pnu.pnuguide.ui.chat.ChatActivity
import com.pnu.pnuguide.ui.course.CourseActivity
import com.pnu.pnuguide.ui.setupHeader1

class StampActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_stamp)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_stamp)
        toolbar.setupHeader1(this, R.string.title_stamp)

        findViewById<MaterialButton>(R.id.btn_download_pdf).setOnClickListener {
            openCampusMapPdf()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_course -> {
                    startActivity(Intent(this, CourseActivity::class.java))
                    true
                }
                R.id.nav_stamp -> {
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    true
                }
                else -> false
            }
        }
        bottomNav.menu.findItem(R.id.nav_stamp).isChecked = true
    }

    private fun openCampusMapPdf() {
        val inputStream = resources.openRawResource(R.raw.campus_map)
        val file = File(getExternalFilesDir(null), "campus_map.pdf")
        if (!file.exists()) {
            FileOutputStream(file).use { output ->
                inputStream.copyTo(output)
            }
        }
        val uri: Uri = FileProvider.getUriForFile(this, "${packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(intent)
    }
}
