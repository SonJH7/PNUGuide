package com.pnu.pnuguide.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.pnu.pnuguide.R
import android.widget.TextView
import android.widget.ProgressBar
import com.pnu.pnuguide.data.QuizProgress
import com.pnu.pnuguide.ui.course.CourseActivity
import com.pnu.pnuguide.ui.stamp.StampActivity

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.btn_continue_course).setOnClickListener {
            startActivity(Intent(requireContext(), CourseActivity::class.java))
        }
        view.findViewById<MaterialButton>(R.id.btn_view_favorites).setOnClickListener {
            startActivity(Intent(requireContext(), CourseActivity::class.java))
        }

        val count = QuizProgress.getCount(requireContext())
        val progressText = view.findViewById<TextView>(R.id.tv_stamp_progress)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_stamp)
        progressText.text = "Stamps Collected: ${'$'}count/24"
        progressBar.max = 24
        progressBar.progress = count
    }
}
