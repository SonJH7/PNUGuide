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
        CourseItem("대학본부", "건물번호: 205", imageRes = R.drawable.head),
        CourseItem("조각공원", "생물관 앞 ", imageRes = R.drawable.piece),
        CourseItem("새벽벌도서관", "건물번호: 420", imageRes = R.drawable.sae_do),
        CourseItem("예원정", "사회관 앞", imageRes = R.drawable.yea),
        CourseItem("금정회관 식당", "건물번호: 419", imageRes = R.drawable.gres),
        CourseItem("경영관", "건물번호: 514", imageRes = R.drawable.busy),
        CourseItem("법학관 모의 법정", "건물번호: 609", imageRes = R.drawable.beopjeong),
        CourseItem("중앙도서관", "건물번호: 510", imageRes = R.drawable.ang),
        CourseItem("음악관", "건물번호: 707", imageRes = R.drawable.music),
        CourseItem("학생회관 식당", "건물번호: 708", imageRes = R.drawable.sres),
        CourseItem("경암체육관", "건물번호: 706", imageRes = R.drawable.p),
        CourseItem("약학관", "건물번호: 503", imageRes = R.drawable.m),
        CourseItem("지질박물관", "건물번호: 414", imageRes = R.drawable.jijil),
        CourseItem("샛벌회관 식당", "건물번호: 415", imageRes = R.drawable.bres),
        CourseItem("박물관", "건물번호: 412", imageRes = R.drawable.museum),
        CourseItem("10.16기념관", "건물번호: 403", imageRes = R.drawable.tensix),
        CourseItem("건설관", "건물번호: 401", imageRes = R.drawable.a),
        CourseItem("기계관 V-SPACE", "건물번호: 303", imageRes = R.drawable.vs),
        CourseItem("진리의 뜰", "운죽정 뒷편", imageRes = R.drawable.jinli),
        CourseItem("인문관", "건물번호: 306", imageRes = R.drawable.inmun),
        CourseItem("넉넉한 터", "건물번호: 203", imageRes = R.drawable.nuck),
        CourseItem("시월 광장", "넉터 앞편", imageRes = R.drawable.ten),
        CourseItem("민주 언덕", "새벽벌 도서관 앞", imageRes = R.drawable.minju),
        CourseItem("미리내 골", "인문관 옆편", imageRes = R.drawable.miri)
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
        displayList.addAll(baseList)
        adapter.submitItems(displayList)

        val cancelBtn: View = findViewById(R.id.btn_cancel_search)
        val scrollView: View = findViewById(R.id.rm27nijip3z)
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
