package practice.com.example.xin.app.firebase.ml

import com.google.firebase.ml.vision.automl.FirebaseAutoMLRemoteModel

class BreedRecognizer{
        val remoteModel = FirebaseAutoMLRemoteModel.Builder(model_name).build()

    companion object {
       private const val model_name = "Cat_Breeds_2019112415841"
    }
}