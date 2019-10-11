package practice.com.example.xin.app.dagger

import dagger.Module
import dagger.Provides
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.api.APIService
import practice.com.example.xin.app.data.DBOpenHelper
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun dbOpenHelper(): DBOpenHelper = DBOpenHelper(app)

    @Provides
    @Singleton
    fun createAPIService(): APIService = APIService.create()
}