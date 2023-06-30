package com.test.testassignment.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.test.testassignment.R

class NotificationsUtil(private val context: Context) {
    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun createNotificationChannel() {
        // need to create channel since api 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.boot_notification_channel),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.boot_notification_channel_description)
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun displayNotification(text: String) {
        val buider = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(R.string.boot_notification_title))
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        //TODO: maybe must be ongoing?

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, buider.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "boot_channel"
        const val NOTIFICATION_ID = 1
    }
}