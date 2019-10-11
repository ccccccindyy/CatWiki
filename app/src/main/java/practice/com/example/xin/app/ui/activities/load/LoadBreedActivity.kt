package practice.com.example.xin.app.ui.activities.load

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_load_breed.*
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity
import pratice.com.example.xin.app.R
import javax.inject.Inject


class LoadBreedActivity : AppCompatActivity() {
    @Inject lateinit var viewModel: LoadBreedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_breed)
        val app = applicationContext as Application
        app.catComponent.inject(this)
        initUI()
        viewModel.compositeDisposable.add(viewModel.loadCatBreeds()
            .subscribeOn(Schedulers.io())
            .doOnError { throw it }
            .subscribe ({
                startActivity(Intent(this, CatDisplayActivity::class.java))
                this.finish()
            }, {
            Snackbar.make(cat_loading, "There's some error while loading, please check your internet connection", Snackbar.LENGTH_LONG)
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
            }))
    }

    override fun onDestroy() {
        viewModel.compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initUI() {
        val uri = Uri.Builder()
            .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
            .path(R.drawable.cat_walking.toString())
            .build()
        val controller:DraweeController  = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true)
            .build()
        cat_loading.controller = controller
    }
}
