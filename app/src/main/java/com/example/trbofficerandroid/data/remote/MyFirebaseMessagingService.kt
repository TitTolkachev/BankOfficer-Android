package com.example.trbofficerandroid.data.remote

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.trbofficerandroid.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            val notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            val channel = NotificationChannel(
                "trb-officer",
                "trb-officer",
                NotificationManager.IMPORTANCE_DEFAULT,
            )

            notificationManager.createNotificationChannel(channel)

            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this, "trb-officer")
                    .setContentTitle(it.title)
                    .setContentText(it.body)
                    .setSmallIcon(R.drawable.payments_24)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setOngoing(false)

            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                Intent(),
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            notificationBuilder.setContentIntent(pendingIntent)

            notificationManager.notify(1, notificationBuilder.build())
        }

    }
}
