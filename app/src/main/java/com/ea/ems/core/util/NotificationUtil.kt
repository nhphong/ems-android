package com.ea.ems.core.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat.*
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.ea.ems.R
import com.ea.ems.presentation.main.MainActivity

interface NotificationUtil {
    fun displayNotification(title: String, message: String, details: String)
}

class NotificationUtilImpl(
    private val appContext: Context
) : NotificationUtil {

    private val appName = appContext.getString(R.string.app_name)
    private val textStyle = BigTextStyle()
    private val pendingIntent = PendingIntent.getActivity(
        appContext,
        0,
        Intent(appContext, MainActivity::class.java)
            .apply { flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK },
        0
    )
    private val notificationManager = NotificationManagerCompat.from(appContext)

    private val notificationBuilder by lazy {
        createNotificationChannel()
        Builder(appContext, appName)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                ResourcesCompat.getDrawable(
                    appContext.resources,
                    R.drawable.ic_salad,
                    null
                )?.toBitmap()
            )
            .setStyle(textStyle)
            .setPriority(PRIORITY_MAX)
            .setDefaults(DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(
                R.drawable.ic_salad,
                "Open App",
                pendingIntent
            )
    }

    override fun displayNotification(title: String, message: String, details: String) {
        notificationBuilder
            .setContentTitle(title)
            .setContentText(message)
        textStyle.bigText(details)
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            val channel = NotificationChannel(appName, appName, IMPORTANCE_HIGH).apply {
                description = appName
            }
            // Register the channel with the system
            val notificationManager =
                appContext.getSystemService(NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }
}
