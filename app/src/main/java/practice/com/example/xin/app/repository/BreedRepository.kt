package practice.com.example.xin.app.repository

import practice.com.example.xin.app.api.APIService
import practice.com.example.xin.app.data.breed.Breed
import javax.inject.Inject

class BreedRepository @Inject constructor(private val apiService: APIService) {

    fun getBreeds(): io.reactivex.Observable<List<Breed>> {
        return apiService.breeds()
    }

}