package practice.com.example.xin.app.ui.cat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.repository.ImageRepository
import javax.inject.Inject

class CatViewModel @Inject constructor(private val breedDAO: BreedDAO, private val imageRepository: ImageRepository) : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    internal val breedLiveData: MutableLiveData<Breed> by lazy {
        MutableLiveData<Breed>()
    }

    internal val imageUrlLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    internal fun initBreed(id: String) {
         breedLiveData.value = breedDAO.getBreed(id)
    }

    internal fun loadImage(): Observable<String> {
       return imageRepository.getImageForCat(breedLiveData.value?.id.toString())
            .map {
                it[0].url
            }

    }


}
