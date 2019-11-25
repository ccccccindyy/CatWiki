package practice.com.example.xin.app.dagger

import dagger.Module
import dagger.Provides
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.firebase.ml.BreedRecognizer
import practice.com.example.xin.app.firebase.ml.VisionImage
import practice.com.example.xin.app.firebase.storage.BreedHelper
import practice.com.example.xin.app.firebase.storage.BreedListDBHelper
import javax.inject.Singleton

@Module
class FirebaseModule(private val app: Application) {

    @Provides
    @Singleton
    fun getBreedListDBHelper(): BreedListDBHelper = BreedListDBHelper()

    @Provides
    @Singleton
    fun getBreedHelper(): BreedHelper = BreedHelper()

    @Provides
    @Singleton
    fun getBreedRecognizer(): BreedRecognizer = BreedRecognizer()

    @Provides
    @Singleton
    fun getVisionImage(): VisionImage = VisionImage()
}