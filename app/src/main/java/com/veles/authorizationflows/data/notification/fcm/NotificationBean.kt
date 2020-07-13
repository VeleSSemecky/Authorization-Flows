package com.veles.authorizationflows.data.notification.fcm

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.veles.authorizationflows.R
import com.veles.authorizationflows.common.Keys
import com.veles.authorizationflows.model.fcm.DataModel
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationActivity


internal object NotificationBean {
    fun sendNotification(context: Context, dataNotification: DataModel) {
        val intent = Intent(context, AuthorizationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context,
            Keys.NotificationData.INTENT_NOTIFICATION_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Keys.NotificationData.INTENT_NOTIFICATION_CHANNEL_ID,
                context.getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = dataNotification.title
            channel.setShowBadge(true)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            mNotificationManager.createNotificationChannel(channel)

        }
        val defaultSoundUri = RingtoneManager
            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(
                context,
                Keys.NotificationData.INTENT_NOTIFICATION_CHANNEL_ID
            )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(dataNotification.title)
                .setContentText(dataNotification.message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        mNotificationManager.notify(
            Keys.NotificationData.INTENT_NOTIFICATION_NOTIFY_ID,
            notificationBuilder.build()
        )
    }
}