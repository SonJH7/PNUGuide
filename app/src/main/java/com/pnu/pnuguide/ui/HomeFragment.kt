package com.pnu.pnuguide.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.pnu.pnuguide.R
import android.widget.TextView
import android.widget.ProgressBar
import android.net.Uri
import com.pnu.pnuguide.data.QuizProgress
import com.pnu.pnuguide.ui.course.CourseActivity
import java.io.File
import java.io.FileOutputStream

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

        view.findViewById<MaterialButton>(R.id.btn_download_map).setOnClickListener {
            openCampusMapPdf()
        }

        val count = QuizProgress.getCount(requireContext())
        val progressText = view.findViewById<TextView>(R.id.tv_stamp_progress)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_stamp)
        progressText.text = "Stamps Collected: $count/24"
        progressBar.max = 24
        progressBar.progress = count
    }

    private fun openCampusMapPdf() {
        val inputStream = resources.openRawResource(R.raw.campus_map)
        val file = File(requireContext().getExternalFilesDir(null), "campus_map.pdf")
        if (!file.exists()) {
            FileOutputStream(file).use { output ->
                inputStream.copyTo(output)
            }
        }
        val uri: Uri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(intent)
    }
}
