package practice.com.example.xin.app.ui.cat

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.firebase.storage.BreedHelper
import practice.com.example.xin.app.firebase.storage.FirebaseHandler
import practice.com.example.xin.app.repository.ImageRepository
import javax.inject.Inject

class CatViewModel @Inject constructor(private val breedHelper: BreedHelper, private val imageRepository: ImageRepository) : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    internal val loadImageSubject: PublishSubject<Boolean> = PublishSubject.create()

    internal val breedLiveData: MutableLiveData<Breed> by lazy {
        MutableLiveData<Breed>()
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    fun initBreed(id: String) {
        breedHelper.addValueListener("breeds",id)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    fun loadImage(): Observable<String> {
       return imageRepository.getImageForCat(breedLiveData.value?.id.toString())
            .map {
               if(it.isNotEmpty() ) it[0].url  else ""
            }
    }

    fun initRealTimeDB(firebaseHandler: FirebaseHandler<Breed>){
        breedHelper.firebaseHandler = firebaseHandler
    }
}
