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
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    private var searchJob: Job? = null
    private lateinit var adapter: CourseSearchAdapter
    // List of buildings displayed when searching.
    // Update names, numbers and images here if needed.
    private val baseList = listOf(
        CourseItem("Building 1", "건물번호: 101", imageRes = R.drawable.cafe),
        CourseItem("Building 2", "건물번호: 102", imageRes = R.drawable.jinli),
        CourseItem("Building 3", "건물번호: 103", imageRes = R.drawable.nuck),
        CourseItem("Building 4", "건물번호: 104", imageRes = R.drawable.cafe),
        CourseItem("Building 5", "건물번호: 105", imageRes = R.drawable.jinli),
        CourseItem("Building 6", "건물번호: 106", imageRes = R.drawable.nuck),
        CourseItem("Building 7", "건물번호: 107", imageRes = R.drawable.cafe),
        CourseItem("Building 8", "건물번호: 108", imageRes = R.drawable.jinli),
        CourseItem("Building 9", "건물번호: 109", imageRes = R.drawable.nuck),
        CourseItem("Building 10", "건물번호: 110", imageRes = R.drawable.cafe),
        CourseItem("Building 11", "건물번호: 111", imageRes = R.drawable.jinli),
        CourseItem("Building 12", "건물번호: 112", imageRes = R.drawable.nuck),
        CourseItem("Building 13", "건물번호: 113", imageRes = R.drawable.cafe),
        CourseItem("Building 14", "건물번호: 114", imageRes = R.drawable.jinli),
        CourseItem("Building 15", "건물번호: 115", imageRes = R.drawable.nuck),
        CourseItem("Building 16", "건물번호: 116", imageRes = R.drawable.cafe),
        CourseItem("Building 17", "건물번호: 117", imageRes = R.drawable.jinli),
        CourseItem("Building 18", "건물번호: 118", imageRes = R.drawable.nuck),
        CourseItem("Building 19", "건물번호: 119", imageRes = R.drawable.cafe),
        CourseItem("Building 20", "건물번호: 120", imageRes = R.drawable.jinli),
        CourseItem("Building 21", "건물번호: 121", imageRes = R.drawable.nuck),
        CourseItem("Building 22", "건물번호: 122", imageRes = R.drawable.cafe),
        CourseItem("Building 23", "건물번호: 123", imageRes = R.drawable.jinli),
        CourseItem("Building 24", "건물번호: 124", imageRes = R.drawable.nuck),
        CourseItem("Building 25", "건물번호: 125", imageRes = R.drawable.cafe)
    )
    private val displayList = mutableListOf<CourseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_course)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_course)
        toolbar.setupHeader1(this, R.string.title_course)

        Glide.with(this).load("https://i.imgur.com/1tMFzp8.png")
            .into(findViewById(R.id.rs49bgap0hwg))

        findViewById<ImageView>(R.id.image_history_1).setImageResource(R.drawable.sae_do)
        findViewById<ImageView>(R.id.image_history_2).setImageResource(R.drawable.vs)
        findViewById<ImageView>(R.id.image_history_3).setImageResource(R.drawable.beopjeong)

        findViewById<ImageView>(R.id.image_history_1).setImageResource(R.drawable.museum)
        findViewById<ImageView>(R.id.image_history_2).setImageResource(R.drawable.jijil)
        findViewById<ImageView>(R.id.image_history_3).setImageResource(R.drawable.ang)
        findViewById<ImageView>(R.id.image_study_1).setImageResource(R.drawable.nuck)
        findViewById<ImageView>(R.id.image_study_2).setImageResource(R.drawable.cafe)
        findViewById<ImageView>(R.id.image_study_3).setImageResource(R.drawable.jinli)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/hc760uhy_expires_30_days.png")
            .into(findViewById(R.id.image_study_2))
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/bnWyQkDlsL/yb4j87iw_expires_30_days.png")
            .into(findViewById(R.id.image_study_3))

        val recycler = findViewById<RecyclerView>(R.id.recycler_search)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = CourseSearchAdapter()
        recycler.adapter = adapter
        adapter.onItemClick = { item ->
            SpotDetailDialogFragment.newInstance(item)
                .show(supportFragmentManager, "detail")
        }
        displayList.addAll(baseList)
        adapter.submitItems(displayList)

        val cancelBtn: View = findViewById(R.id.btn_cancel_search)
        val scrollView: View = findViewById(R.id.rm27nijip3z)
        val categoryRow: View = findViewById(R.id.r0ynj2pkd7ew9)
        val sectionPopular: View = findViewById(R.id.section_popular)
        val sectionHistory: View = findViewById(R.id.section_history)
        val sectionStudy: View = findViewById(R.id.section_study)

        fun showDetail(title: String, desc: String, image: Int) {
            val item = CourseItem(title, desc, imageRes = image)
            SpotDetailDialogFragment.newInstance(item)
                .show(supportFragmentManager, "detail")
        }

        findViewById<View>(R.id.r4426ybbx5od).setOnClickListener {
            showDetail("새벽벌 도서관", "건물번호: 420", R.drawable.sae_do)
        }
        findViewById<View>(R.id.r0r21uflcziqi).setOnClickListener {
            showDetail("PNU V-SPACE", "건물번호: 303", R.drawable.vs)
        }
        findViewById<View>(R.id.r0augc34kguoi).setOnClickListener {
            showDetail("법학관 모의 법정", "건물번호: 609", R.drawable.beopjeong)
        }
        findViewById<View>(R.id.card_history_1).setOnClickListener {
            showDetail("박물관", "건물 번호: 412", R.drawable.museum)
        }
        findViewById<View>(R.id.card_history_2).setOnClickListener {
            showDetail("지질박물관", "건물 번호: 414", R.drawable.jijil)
        }
        findViewById<View>(R.id.card_history_3).setOnClickListener {
            showDetail("중앙 도서관", "건물 번호: 510", R.drawable.ang)
        }
        findViewById<View>(R.id.card_study_1).setOnClickListener {
            showDetail("넉넉한 터", "건물번호: 203", R.drawable.nuck)
        }
        findViewById<View>(R.id.card_study_2).setOnClickListener {
            showDetail("cafe 운죽정", "건물번호: 202", R.drawable.cafe)
        }
        findViewById<View>(R.id.card_study_3).setOnClickListener {
            showDetail("진리의 뜰", "운죽정 뒷편", R.drawable.jinli)
        }

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
                scrollView.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                cancelBtn.visibility = View.VISIBLE
            }
        }
        cancelBtn.setOnClickListener {
            recycler.visibility = View.GONE
            categoryRow.visibility = View.VISIBLE
            showSection(sectionPopular)
            scrollView.visibility = View.VISIBLE
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
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(300)
                    searchQuery = s.toString()
                    filterList()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (!rv.canScrollVertically(1)) {
                    val more = if (searchQuery.isEmpty()) baseList else baseList.filter {
                        it.title.contains(searchQuery, ignoreCase = true)
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
            it.title.contains(searchQuery, ignoreCase = true)
        }
        displayList.clear()
        displayList.addAll(filtered)
        adapter.submitItems(displayList)
    }
}
