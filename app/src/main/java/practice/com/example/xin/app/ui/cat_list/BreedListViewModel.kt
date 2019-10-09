package practice.com.example.xin.app.ui.cat_list

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.repository.BreedRepository
import javax.inject.Inject


class BreedListViewModel @Inject constructor(private val breedDAO: BreedDAO): ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    internal fun getBreeds() :List<Breed> {
        return breedDAO.getBreeds()
    }
}