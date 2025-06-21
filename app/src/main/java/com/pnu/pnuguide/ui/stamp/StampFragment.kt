package com.pnu.pnuguide.ui.stamp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.GridLayoutManager

import com.pnu.pnuguide.databinding.FragmentStampBinding

class StampFragment : Fragment() {

    private var _binding: FragmentStampBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<StampViewModel> {
        AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            if (bitmap != null) {
                viewModel.processBitmap(bitmap)
            } else {
                Toast.makeText(requireContext(), getString(com.pnu.pnuguide.R.string.stamp_failed), Toast.LENGTH_SHORT).show()
            }
        }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) takePicture.launch(null)
            else Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStampBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerStamps.layoutManager = GridLayoutManager(requireContext(), 4)
        val adapter = StampAdapter()
        binding.recyclerStamps.adapter = adapter

        viewModel.stamps.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.error.observe(viewLifecycleOwner) { failed ->
            if (failed) {
                Toast.makeText(requireContext(), getString(com.pnu.pnuguide.R.string.stamp_failed), Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }

        binding.toolbarStamp.setNavigationOnClickListener { requireActivity().finish() }
        binding.buttonCapture.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                takePicture.launch(null)
            } else {
                requestPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
