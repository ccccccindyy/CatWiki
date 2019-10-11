package practice.com.example.xin.app.ui.breed

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import javax.inject.Inject


class BreedListViewModel @Inject constructor(private val breedDAO: BreedDAO): ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    fun getBreeds(): List<Breed> {
        return breedDAO.getBreeds()
    }
}