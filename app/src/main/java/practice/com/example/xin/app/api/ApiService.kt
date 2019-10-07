package practice.com.example.xin.app.api

import okhttp3.OkHttpClient
import practice.com.example.xin.app.api.Constants.Companion.BASE_URL
import practice.com.example.xin.app.api.Constants.Companion.BREEDS
import practice.com.example.xin.app.data.breed.Breed
import java.util.concurrent.TimeUnit

interface APIService {

    @retrofit2.http.GET(BREEDS)
    fun breeds(): io.reactivex.Observable<List<Breed>>

    companion object Factory {

        private var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()

        fun create(): APIService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}