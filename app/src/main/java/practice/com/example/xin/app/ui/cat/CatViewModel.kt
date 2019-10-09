package practice.com.example.xin.app.ui.cat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import javax.inject.Inject

class CatViewModel @Inject constructor(private val breedDAO: BreedDAO) : ViewModel() {
    internal val breed: MutableLiveData<Breed> by lazy {
        MutableLiveData<Breed>()
    }


    internal fun initBreed(id: String) {
         breed.value = breedDAO.getBreed(id)
    }
}
