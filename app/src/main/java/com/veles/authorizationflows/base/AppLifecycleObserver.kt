package com.veles.authorizationflows.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.veles.authorizationflows.data.local.data.DataStore


class AppLifecycleObserver(private val dataStore: DataStore) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
        dataStore.setIsInForeground(true)
//        PreferencesUtils.save(Constants.IN_FOREGROUND, true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        dataStore.setIsInForeground(false)
//        PreferencesUtils.save(Constants.IN_FOREGROUND, false)
    }
}