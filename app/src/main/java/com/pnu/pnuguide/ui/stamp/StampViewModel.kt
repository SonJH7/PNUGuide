package com.pnu.pnuguide.ui.stamp

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnu.pnuguide.data.CourseData
import com.pnu.pnuguide.data.MLImageHelper
import com.pnu.pnuguide.data.Stamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class StampViewModel(private val app: Application) : AndroidViewModel(app) {

    private val prefs = app.getSharedPreferences("stamps", 0)

    private val _stamps = MutableLiveData<List<Stamp>>(loadStamps())
    val stamps: LiveData<List<Stamp>> = _stamps

    private fun loadStamps(): List<Stamp> {
        val fromCourse = CourseData.loadStamps()
        return fromCourse.mapIndexed { index, stamp ->
            val collected = prefs.getBoolean(stamp.id, false)
            stamp.copy(collected = collected)
        }
    }

    private fun saveStamp(id: String) {
        prefs.edit().putBoolean(id, true).apply()
    }

    fun processImage(file: File, targetId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            processBitmap(bitmap, targetId)
        }
    }

    fun processBitmap(bitmap: Bitmap, targetId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val match = MLImageHelper.matchSpot(bitmap)
            if (match == targetId) {
                markStamp(targetId)
            } else {
                _error.postValue(true)
            }
        }
    }

    private fun markStamp(id: String) {
        val current = _stamps.value?.map {
            if (it.id == id) {
                saveStamp(id)
                it.copy(collected = true)
            } else it
        } ?: emptyList()
        _stamps.postValue(current)
    }

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    fun clearError() { _error.value = false }
}
