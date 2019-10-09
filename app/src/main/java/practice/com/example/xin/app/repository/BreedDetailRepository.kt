package practice.com.example.xin.app.repository

import practice.com.example.xin.app.api.APIService
import practice.com.example.xin.app.data.breed.Breed

class BreedDetailRepository {
    fun getBreedDetail(): io.reactivex.Observable<List<Breed>> {
        return APIService.create().breeds()
    }
}