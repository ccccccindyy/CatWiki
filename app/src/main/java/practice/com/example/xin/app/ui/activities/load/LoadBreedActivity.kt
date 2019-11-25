package practice.com.example.xin.app.ui.activities.load

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import io.reactivex.internal.util.HalfSerializer.onComplete
import kotlinx.android.synthetic.main.activity_load_breed.*
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.firebase.ml.LoadModelCallBack
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity
import pratice.com.example.xin.app.R
import javax.inject.Inject


class LoadBreedActivity : AppCompatActivity(), LoadModelCallBack {
    @Inject lateinit var viewModel: LoadBreedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_breed)
        val app = applicationContext as Application
        app.catComponent.inject(this)
        initUI()
        viewModel.loadCatModels(this)
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

    override fun onModelLoaded() {
        startActivity(Intent(this, CatDisplayActivity::class.java))
        this.finish()    }
}
