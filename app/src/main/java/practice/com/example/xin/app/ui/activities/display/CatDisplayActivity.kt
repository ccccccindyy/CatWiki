package practice.com.example.xin.app.ui.activities.display

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_app_display.*
import android.content.Intent
import android.net.Uri


class CatDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pratice.com.example.xinzhang.recyclerview.R.layout.activity_app_display)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:cindy_september@hotmail.com")
            startActivity(emailIntent)
        }
    }

}
