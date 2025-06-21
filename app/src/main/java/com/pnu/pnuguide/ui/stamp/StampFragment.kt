package com.pnu.pnuguide.ui.stamp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.GridLayoutManager
import java.io.File

import com.pnu.pnuguide.databinding.FragmentStampBinding

class StampFragment : Fragment() {

    private var _binding: FragmentStampBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<StampViewModel> {
        AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) startCamera()
            else Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
        }

    private var imageCapture: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null

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
                if (cameraProvider == null) startCamera() else captureImage()
            } else {
                requestPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun startCamera() {
        val providerFuture = ProcessCameraProvider.getInstance(requireContext())
        providerFuture.addListener({
            cameraProvider = providerFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.previewView.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder().build()
            val selector = CameraSelector.DEFAULT_BACK_CAMERA
            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(this, selector, preview, imageCapture)
            binding.previewView.visibility = View.VISIBLE
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun captureImage() {
        val capture = imageCapture ?: return
        val file = File(requireContext().cacheDir, "stamp_${System.currentTimeMillis()}.jpg")
        val output = ImageCapture.OutputFileOptions.Builder(file).build()
        capture.takePicture(output, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                binding.previewView.visibility = View.GONE
                cameraProvider?.unbindAll()
                cameraProvider = null
                viewModel.processImage(file)
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(requireContext(), getString(com.pnu.pnuguide.R.string.stamp_failed), Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        cameraProvider?.unbindAll()
        cameraProvider = null
        _binding = null
    }
}
