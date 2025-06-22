package com.pnu.pnuguide.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.pnu.pnuguide.R
import com.pnu.pnuguide.ui.course.CourseActivity
import com.pnu.pnuguide.ui.stamp.StampActivity
import android.widget.TextView
import com.pnu.pnuguide.data.StampProgress

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
        view.findViewById<MaterialButton>(R.id.btn_course).setOnClickListener {
            startActivity(Intent(requireContext(), CourseActivity::class.java))
        }
        view.findViewById<MaterialButton>(R.id.btn_stamp).setOnClickListener {
            startActivity(Intent(requireContext(), StampActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val progress = StampProgress.getProgress(requireContext())
        view?.findViewById<TextView>(R.id.text_stamp_progress)?.text =
            getString(R.string.stamp_progress_format, progress, StampProgress.MAX)
    }
}
