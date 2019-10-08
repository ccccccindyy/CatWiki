package practice.com.example.xin.app.ui.activities.display

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Intent
import android.net.Uri
import androidx.navigation.Navigation
import pratice.com.example.xinzhang.recyclerview.R


class CatDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pratice.com.example.xinzhang.recyclerview.R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:cindy_september@hotmail.com")
            startActivity(emailIntent)
        }
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

}
