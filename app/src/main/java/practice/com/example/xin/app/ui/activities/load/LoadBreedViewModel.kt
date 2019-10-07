package practice.com.example.xin.app.ui.activities.load

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.repository.BreedRepository


class LoadBreedViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    internal fun loadCatBreeds() : Observable<List<Breed>> {
        return BreedRepository().getBreeds()
    }
}