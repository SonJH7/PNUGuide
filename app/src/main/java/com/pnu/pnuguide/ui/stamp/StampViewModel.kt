package com.pnu.pnuguide.ui.stamp

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnu.pnuguide.data.MLImageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StampViewModel : ViewModel() {

    private val _stamps = MutableStateFlow<List<String>>(emptyList())
    val stamps: StateFlow<List<String>> = _stamps

    fun processImage(bitmap: Bitmap) {
        viewModelScope.launch {
            // TODO: run ML model
            val match = MLImageHelper.matchSpot(bitmap)
            if (match != null) {
                // TODO: update Firestore
            }
        }
    }
}