package com.pnu.pnuguide.data

import android.graphics.Bitmap

object MLImageHelper {
    suspend fun matchSpot(bitmap: Bitmap): String? {
        // TODO: run TensorFlow Lite model and return spot id if similarity over threshold
        return null
    }
}