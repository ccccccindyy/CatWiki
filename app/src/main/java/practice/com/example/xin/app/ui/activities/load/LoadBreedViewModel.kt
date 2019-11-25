package practice.com.example.xin.app.ui.activities.load

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import practice.com.example.xin.app.firebase.ml.BreedRecognizer
import practice.com.example.xin.app.firebase.ml.LoadModelCallBack
import javax.inject.Inject


class LoadBreedViewModel @Inject constructor(
    private val breedRecognizer: BreedRecognizer
) : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    fun loadCatModels(loadModelCallBack: LoadModelCallBack) {
        breedRecognizer.loadModel(loadModelCallBack)
    }
}