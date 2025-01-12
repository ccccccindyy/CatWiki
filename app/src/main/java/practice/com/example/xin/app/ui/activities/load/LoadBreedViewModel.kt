package practice.com.example.xin.app.ui.activities.load

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.repository.BreedRepository
import javax.inject.Inject


class LoadBreedViewModel @Inject constructor(
    private val breedDAO: BreedDAO,
    private val breedRepository: BreedRepository
) : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    fun loadCatBreeds(): Observable<List<Boolean>> {
        return breedRepository.getBreeds()
            .flatMapIterable { breed -> breed }
            .map { breedDAO.addBreed(breed = it) }
            .toList()
            .toObservable()
    }
}