package practice.com.example.xin.app.ui.activities.load

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import kotlinx.android.synthetic.main.activity_load_breed.*
import pratice.com.example.xinzhang.recyclerview.R
import io.reactivex.schedulers.Schedulers
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity


class LoadBreedActivity : AppCompatActivity() {

    private val viewModel: LoadBreedViewModel =
        LoadBreedViewModel()
    private val breedDAO: BreedDAO = BreedDAO(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_breed)
        initUI()
        viewModel.compositeDisposable.add(viewModel.loadCatBreeds()
            .flatMapIterable { breed -> breed }
            .map{breedDAO.addBreed(breed = it)}
            .toList()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .doOnError{throw it}
            .subscribe {
               startActivity( Intent(this, CatDisplayActivity::class.java))
                this.finish()
            })
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
            .build();
        cat_loading.controller = controller
    }
}
