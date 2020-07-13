package com.veles.authorizationflows.data.notification.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.veles.authorizationflows.data.local.data.DataStore
import com.veles.authorizationflows.model.fcm.DataModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class NotificationFirebaseMessagingServiceImpl : FirebaseMessagingService(),
    NotificationFirebaseMessagingService {

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate() {
        AndroidInjection.inject(this)
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val dataModel = Gson().fromJson(Gson().toJson(remoteMessage.data), DataModel::class.java)
        if(dataModel.tokenSender != dataStore.fcmToken && dataStore.isInForeground.not())
        NotificationBean.sendNotification(applicationContext,dataModel)
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        dataStore.fcmToken = s
        Log.i("NotificationFirebase", "onNewToken: $s")
    }


}