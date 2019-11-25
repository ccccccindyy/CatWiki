package practice.com.example.xin.app.firebase.ml

import android.util.Log
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.automl.FirebaseAutoMLRemoteModel
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions

class BreedRecognizer{

    lateinit var loadLoadModelCallback: LoadModelCallBack

    lateinit var processImageCallBack: ProcessImageCallBack

    lateinit var breedLabeler: FirebaseVisionImageLabeler

    private val remoteModel = FirebaseAutoMLRemoteModel.Builder(model_name).build()

    fun loadModel(loadModelCallBack: LoadModelCallBack){
        this.loadLoadModelCallback = loadModelCallBack
        val conditions = FirebaseModelDownloadConditions.Builder()
            .requireWifi()
            .build()
        FirebaseModelManager.getInstance().download(remoteModel, conditions)
            .addOnCompleteListener {
                val options = FirebaseVisionOnDeviceAutoMLImageLabelerOptions.Builder(remoteModel)
                    .setConfidenceThreshold(0.5f)
                    .build()
                breedLabeler = FirebaseVision.getInstance().getOnDeviceAutoMLImageLabeler(options)
                loadLoadModelCallback.onModelLoaded()
            }
    }

    fun processImage(firebaseVisionImage:FirebaseVisionImage){
        breedLabeler.processImage(firebaseVisionImage)
            .addOnSuccessListener { labels ->
               if(!labels.isNullOrEmpty()){
                   labels.first()?.let{
                       processImageCallBack.onProcessingSucceed(it.text, "${it.confidence * 100}%", firebaseVisionImage.bitmap)
                   }
               }
            }
            .addOnFailureListener {
                processImageCallBack.onProcessingFailed()
            }
    }


    companion object {
       private const val model_name = "Cat_Breeds_2019112415841"
    }
}