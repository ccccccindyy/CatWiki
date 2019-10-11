package practice.com.example.xin.app.repository

import io.reactivex.Observable
import practice.com.example.xin.app.api.APIService
import practice.com.example.xin.app.data.img.Image
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: APIService) {
    fun getImageForCat(id:String): Observable<List<Image>> {
        return apiService.getImage(id)
    }
}