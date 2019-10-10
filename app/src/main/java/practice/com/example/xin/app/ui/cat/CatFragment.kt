package practice.com.example.xin.app.ui.cat

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_load_breed.*
import kotlinx.android.synthetic.main.cat_fragment.*
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity
import pratice.com.example.xinzhang.recyclerview.R
import javax.inject.Inject


class CatFragment : Fragment() {


    companion object {
        const val BREED_ID_ARG = "BREED_ID_ARG"
    }

    @Inject
    lateinit var viewModel: CatViewModel

    override fun onAttach(context: Context) {
        val app = activity?.applicationContext as Application
        app.catComponent.inject(this)
        super.onAttach(context)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val supportActionBar = (activity as CatDisplayActivity).supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        val breedObserver = Observer<Breed> {
            tvDescription.text = it.description
            tvTemperament.text = it.temperament
            tvHypoValue.text =
                if (it.hypoallergenic == 1) getString(R.string.yes) else getString(R.string.no)
            tvOriginValue.text = it.origin.toString()
            supportActionBar?.title = it.name
        }
        viewModel.breedLiveData.observe(this, breedObserver)
        viewModel.imageUrlLiveData.observe(this, Observer {
            ivProfilePic.setImageURI(it)
            ibRefresh.visibility = View.VISIBLE
        })
        arguments?.getString(BREED_ID_ARG)?.let {
            viewModel.initBreed(it)
        }

        return inflater.inflate(R.layout.cat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPlaceHolder()
        viewModel.loadImageSubject.onNext(true)
        viewModel.compositeDisposable.add(
            viewModel.loadImage()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError { throw it }
            .subscribe({
                viewModel.imageUrlLiveData.value = it
            }, {
                Snackbar.make(cat_loading, "There's some error while loading, please check your internet connection", Snackbar.LENGTH_LONG)
                    .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    .show()
            }))


        ibRefresh.setOnClickListener {
            viewModel.loadImageSubject.onNext(true)
        }

        viewModel.compositeDisposable.add(viewModel.loadImageSubject
            .doOnNext {
                loadPlaceHolder()
                ibRefresh.visibility = View.INVISIBLE
            }
            .flatMap { viewModel.loadImage().subscribeOn(Schedulers.io()) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.imageUrlLiveData.value = it
            }, {
                Snackbar.make(
                    cat_loading,
                    "There's some error while loading, please check your internet connection",
                    Snackbar.LENGTH_LONG
                )
                    .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    .show()
            })
        )
    }

    override fun onDestroyView() {
        (activity as CatDisplayActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        viewModel.compositeDisposable.clear()
        setHasOptionsMenu(false)
        super.onDestroyView()
    }

    private fun loadPlaceHolder() {
        val uri = Uri.Builder()
            .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
            .path(R.drawable.cat_walking.toString())
            .build()
        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true)
            .build();
        ivProfilePic.controller = controller
    }

}
