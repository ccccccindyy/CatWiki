package practice.com.example.xin.app.ui.activities.display


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navGraphViewModels
import com.crashlytics.android.Crashlytics
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_app_display.*
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.firebase.ml.ProcessImageCallBack
import practice.com.example.xin.app.ui.breed.BreedListFragment
import practice.com.example.xin.app.ui.cat.CatFragment
import practice.com.example.xin.app.ui.recogizer.CatRecognizerResultFragment
import pratice.com.example.xin.app.R
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject


class CatDisplayActivity : AppCompatActivity(), ProcessImageCallBack{
    @Inject
    lateinit var viewModel: CatDisplayViewModel

    var msgCatId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pratice.com.example.xin.app.R.layout.activity_app_display)
        setSupportActionBar(toolbar)
        val app = applicationContext as Application
        app.catComponent.inject(this)
        fab.setOnClickListener {
            val photoPickerIntent: Intent =  Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 0);
        }
        crashFab.setOnClickListener{
            Crashlytics.getInstance().crash()
        }
        viewModel.setProcessImageCallback(this)
    }

    override fun onResume() {
        super.onResume()
        intent.extras?.get("cat_id")?.let { breedId ->
            val navHostFragment: NavHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navHostFragment.childFragmentManager.fragments[0]?.let {
                (it as BreedListFragment).navigateToCatFragment(breedId as String)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
              try {
                  data?.data?.let {
                      viewModel.recognizeImage(imageFromPath(this, it))
                  }

            } catch ( e: FileNotFoundException) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    private fun imageFromPath(context: Context, uri: Uri): FirebaseVisionImage? {
        return try {
            FirebaseVisionImage.fromFilePath(context, uri)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun onProcessingSucceed(label: String, confident: String, bitmap: Bitmap) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val image = stream.toByteArray()
        CatRecognizerResultFragment.newInstance(label, confident, image).show(supportFragmentManager,"tag")
    }

    override fun onProcessingFailed() {
       Toast.makeText(this, getString(R.string.process_cat_failed),Toast.LENGTH_SHORT).show()
    }
}
