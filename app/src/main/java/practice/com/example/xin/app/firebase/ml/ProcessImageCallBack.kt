package practice.com.example.xin.app.firebase.ml

import android.graphics.Bitmap
import android.net.Uri

interface ProcessImageCallBack {
    fun onProcessingSucceed(label: String, confident: String, bitmap: Bitmap)

    fun onProcessingFailed()

}