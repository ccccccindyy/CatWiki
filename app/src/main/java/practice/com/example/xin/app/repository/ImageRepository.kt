package practice.com.example.xin.app.repository

import io.reactivex.Observable
import practice.com.example.xin.app.api.APIService
import practice.com.example.xin.app.data.Image
import javax.inject.Inject

class ImageRepository @Inject constructor() {
    fun getImageForCat(id:String): Observable<List<Image>> {
        return APIService.create().getImage(id)
    }
}