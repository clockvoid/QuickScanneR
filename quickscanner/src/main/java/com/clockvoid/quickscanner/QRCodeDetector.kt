package com.clockvoid.quickscanner

import android.graphics.Bitmap
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import java.lang.Exception

object QRCodeDetector {
    private val options = FirebaseVisionBarcodeDetectorOptions.Builder()
        .setBarcodeFormats(
            FirebaseVisionBarcode.FORMAT_QR_CODE
        )
        .build()

    private val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)

    fun detect(
        bitmap: Bitmap,
        onSuccess: (images: List<FirebaseVisionBarcode>) -> Unit,
        onFailure: (func: Exception) -> Unit
    ) {
        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap)
        detect(firebaseVisionImage, onSuccess, onFailure)
    }

    fun detect(
        image: FirebaseVisionImage,
        onSuccess: (images: List<FirebaseVisionBarcode>) -> Unit,
        onFailure: (func: Exception) -> Unit
    ) {

        detector
            .detectInImage(image)
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)
    }
}
