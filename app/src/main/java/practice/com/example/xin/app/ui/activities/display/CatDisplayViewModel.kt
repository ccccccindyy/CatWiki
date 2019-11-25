package practice.com.example.xin.app.ui.activities.display

import androidx.lifecycle.ViewModel
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import practice.com.example.xin.app.firebase.ml.BreedRecognizer
import practice.com.example.xin.app.firebase.ml.ProcessImageCallBack
import javax.inject.Inject


class CatDisplayViewModel @Inject constructor(
    private val breedRecognizer: BreedRecognizer
) : ViewModel() {

    fun recognizeImage(visionImage: FirebaseVisionImage?){
        visionImage?.let {
            breedRecognizer.processImage(visionImage)
        }
    }

    fun setProcessImageCallback(processImageCallBack: ProcessImageCallBack){
          breedRecognizer.processImageCallBack = processImageCallBack
    }
}