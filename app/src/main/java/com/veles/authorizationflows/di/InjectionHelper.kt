package com.veles.authorizationflows.di

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.veles.authorizationflows.AppApplication
import com.veles.authorizationflows.base.core.SimpleActivityLifecycleCallbacks
import com.veles.authorizationflows.di.component.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

object InjectionHelper {
    fun init(_application: AppApplication) {
        DaggerAppComponent.builder()
                .application(_application)
                .build()
                .inject(_application)
        registerActivityInjector(_application)
    }

    private fun registerActivityInjector(_application: AppApplication) {
        _application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks() {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                super.onActivityCreated(activity, savedInstanceState)
                injectActivity(activity)
            }
        })
    }

    private fun injectActivity(_activity: Activity) {
        if (_activity is Injectable) {
            AndroidInjection.inject(_activity)
        }
        if (_activity is FragmentActivity) {
            //TODO probably need to unregister when activity destroyed
            _activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(fm: FragmentManager, f: Fragment,
                                                               savedInstanceState: Bundle?) {
                                    if (f is Injectable) {
                                        AndroidSupportInjection.inject(f)
                                    }
                                }
                            }, true)
        }
    }

}