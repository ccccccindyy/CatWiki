package practice.com.example.xin.app.ui.cat_list

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.repository.BreedRepository


class CatListViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    internal fun loadCat() : Observable<List<Breed>> {
        return BreedRepository().getBreeds()
    }
}