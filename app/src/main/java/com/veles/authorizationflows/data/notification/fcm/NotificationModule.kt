package com.veles.authorizationflows.data.notification.fcm

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NotificationModule {

    @ContributesAndroidInjector
    abstract fun notificationFirebaseMessagingService(): NotificationFirebaseMessagingServiceImpl

}