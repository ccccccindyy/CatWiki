package practice.com.example.xin.app

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

class Application : Application() {

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
        Fresco.initialize(context)
    }
}