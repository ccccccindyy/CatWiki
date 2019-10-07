package practice.com.example.xin.app.ui.activities.load

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import kotlinx.android.synthetic.main.activity_main.*
import pratice.com.example.xinzhang.recyclerview.R
import io.reactivex.schedulers.Schedulers
import practice.com.example.xin.app.data.breed.BreedDAO


class LoadBreedActivity : AppCompatActivity() {

    private val viewModel: LoadBreedViewModel =
        LoadBreedViewModel()
    private val breedDAO: BreedDAO = BreedDAO(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

    }

    override fun onResume() {
        super.onResume()
        viewModel.compositeDisposable.add(viewModel.loadCatBreeds()
            .subscribeOn(Schedulers.io())
            .flatMapIterable { breed -> breed }
            .map{breedDAO.addBreed(breed = it)}
            .toList()
            .toObservable()
            .subscribe {
                    //save all catss
                Log.d("BREEDS",breedDAO.getBreeds().toString() )
            })
    }

    override fun onPause() {
        super.onPause()
        viewModel.compositeDisposable.clear()
    }



    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()

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
