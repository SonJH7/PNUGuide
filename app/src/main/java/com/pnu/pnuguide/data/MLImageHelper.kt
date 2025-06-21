package com.pnu.pnuguide.data

import android.graphics.Bitmap

object MLImageHelper {
    suspend fun matchSpot(bitmap: Bitmap): String? {
        // TODO: replace this placeholder logic with TensorFlow Lite model inference
        return if (bitmap.width > 0 && bitmap.height > 0) "dummy_spot" else null
    }
}