package practice.com.example.xin.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigPictureStyle
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity
import pratice.com.example.xin.app.R
import java.io.InputStream
import java.net.URL


class MyFirebaseMessageService: FirebaseMessagingService() {

    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)
        msg.notification?.let {
            sendNotification(it.body, it.imageUrl, msg.data)
        }
    }


    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }

    private fun sendNotification(messageBody: String?, image: Uri?, extras: Map<String, String>?){
        val intent =  Intent(this, CatDisplayActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        if(extras!=null && extras["cat_id"]!=null){
            extras["cat_id"]?.let {
                intent.putExtra("cat_id", it)
            }
        }
        val pendingIntent:PendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        val channelId:String = "CHANNEL_ID";
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
       val notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_pets_black_24dp)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
        if(image!=null){
            val style = BigPictureStyle()
            val url = URL (image.toString())
            style.bigPicture( BitmapFactory.decodeStream(url.openConnection().getInputStream()))
            notificationBuilder.setStyle(style)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =  NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }



    companion object {
       private const val TAG = "FCM Service"
    }
}