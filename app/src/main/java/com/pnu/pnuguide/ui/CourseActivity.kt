package com.pnu.pnuguide.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnu.pnuguide.R
import com.pnu.pnuguide.MainActivity
import com.pnu.pnuguide.ui.chat.ChatActivity
import com.pnu.pnuguide.ui.map.MapActivity
import com.pnu.pnuguide.ui.profile.ProfileActivity
import com.pnu.pnuguide.ui.setupHeader1

class CourseActivity : AppCompatActivity() {
    private var searchQuery: String = ""
    private lateinit var adapter: CourseSearchAdapter
    private val baseList = listOf(
        "Campus Highlights Tour",
        "Historic Landmarks Tour",
        "Nature Discovery Walk",
        "Science Exploration Course",
        "Library Tour"
    )
    private val displayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_course)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_course)
        toolbar.setupHeader1(this, R.string.title_course)

        Glide.with(this).load("https://i.imgur.com/1tMFzp8.png")
            .into(findViewById(R.id.rs49bgap0hwg))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/z7s2j8n3_expires_30_days.png")
            .into(findViewById(R.id.rl432c43q9nj))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/hc760uhy_expires_30_days.png")
            .into(findViewById(R.id.rd7w45soo08))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/yb4j87iw_expires_30_days.png")
            .into(findViewById(R.id.rzafkzrm2ak))

        val recycler = findViewById<RecyclerView>(R.id.recycler_search)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = CourseSearchAdapter()
        recycler.adapter = adapter
        displayList.addAll(baseList)
        adapter.submitItems(displayList)

        val cancelBtn: View = findViewById(R.id.btn_cancel_search)
        val categoryRow: View = findViewById(R.id.r0ynj2pkd7ew9)
        val sectionPopular: View = findViewById(R.id.section_popular)
        val sectionHistory: View = findViewById(R.id.section_history)
        val sectionStudy: View = findViewById(R.id.section_study)

        fun showSection(target: View) {
            sectionPopular.visibility = if (target == sectionPopular) View.VISIBLE else View.GONE
            sectionHistory.visibility = if (target == sectionHistory) View.VISIBLE else View.GONE
            sectionStudy.visibility = if (target == sectionStudy) View.VISIBLE else View.GONE
        }

        val searchEdit: EditText = findViewById(R.id.r9hg358v9thk)
        searchEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && recycler.visibility != View.VISIBLE) {
                categoryRow.visibility = View.GONE
                sectionPopular.visibility = View.GONE
                sectionHistory.visibility = View.GONE
                sectionStudy.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                cancelBtn.visibility = View.VISIBLE
            }
        }
        cancelBtn.setOnClickListener {
            recycler.visibility = View.GONE
            categoryRow.visibility = View.VISIBLE
            showSection(sectionPopular)
            cancelBtn.visibility = View.GONE
            searchEdit.text.clear()
            searchEdit.clearFocus()
            displayList.clear()
            displayList.addAll(baseList)
            adapter.submitItems(displayList)
        }
        searchEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchQuery = s.toString()
                filterList()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (!rv.canScrollVertically(1)) {
                    val more = if (searchQuery.isEmpty()) baseList else baseList.filter {
                        it.contains(searchQuery, ignoreCase = true)
                    }
                    val start = displayList.size
                    displayList.addAll(more)
                    adapter.appendItems(more)
                    adapter.notifyItemRangeInserted(start, more.size)
                }
            }
        })

        findViewById<TextView>(R.id.rguxz9lullzn).setOnClickListener {
            showSection(sectionPopular)
        }
        findViewById<TextView>(R.id.r2qlk2kqy8g4).setOnClickListener {
            showSection(sectionHistory)
        }
        findViewById<TextView>(R.id.rygry7veadsj).setOnClickListener {
            showSection(sectionStudy)
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_map -> {
                    startActivity(Intent(this, MapActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    true
                }
                else -> false
            }
        }
        bottomNav.menu.findItem(R.id.nav_home).isChecked = true
    }

    private fun filterList() {
        val filtered = if (searchQuery.isEmpty()) baseList else baseList.filter {
            it.contains(searchQuery, ignoreCase = true)
        }
        displayList.clear()
        displayList.addAll(filtered)
        adapter.submitItems(displayList)
    }
}
