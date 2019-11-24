package practice.com.example.xin.app

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import practice.com.example.xin.app.dagger.AppComponent
import practice.com.example.xin.app.dagger.AppModule
import practice.com.example.xin.app.dagger.DaggerAppComponent
import practice.com.example.xin.app.dagger.FirebaseModule

class Application : Application() {

    lateinit var catComponent: AppComponent

    init {
        instance = this
    }

    companion object {
        private var instance: practice.com.example.xin.app.Application? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext

        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = applicationContext()
        catComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .firebaseModule(FirebaseModule(this))
            .application(this)
            .build()
        catComponent.inject(this)
        Fresco.initialize(context)
    }
}