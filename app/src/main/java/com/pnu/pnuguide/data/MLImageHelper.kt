package com.pnu.pnuguide.data

import android.graphics.Bitmap
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.NormalizeOp

object MLImageHelper {
    fun bitmapToTensorImage(bitmap: Bitmap): TensorImage {
        val processor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .add(NormalizeOp(0f, 255f))
            .build()
        val tensor = TensorImage(DataType.FLOAT32)
        tensor.load(bitmap)
        return processor.process(tensor)
    }

    fun bitmapToTensorBuffer(bitmap: Bitmap): TensorBuffer {
        return bitmapToTensorImage(bitmap).tensorBuffer
    }
    suspend fun matchSpot(bitmap: Bitmap): String? {
        // TODO: replace this placeholder logic with TensorFlow Lite model inference
        return if (bitmap.width > 0 && bitmap.height > 0) "dummy_spot" else null
    }
}

