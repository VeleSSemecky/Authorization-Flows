package com.veles.authorizationflows.presentation.splash

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationActivity
import com.veles.authorizationflows.presentation.main.activity.MainActivity
import org.checkerframework.checker.units.qual.A
import javax.inject.Inject

class SplashViewModel @Inject internal constructor(private val splashModel: SplashModel) :
    SplashContract.ViewModel(splashModel) {

    private var isFirstStart = true

    override fun <T : Fragment> openScreen(java: Class<T>): LiveData<Class<T>> {
        val screen = when (isFirstStart) {
            true -> MutableLiveData<Class<T>>(java)
            false -> MutableLiveData()
        }
        isFirstStart = false
        return screen
    }

    override fun openScreen():Class<out Activity> {
        return when (splashModel.isAuthOrNot()) {
            true -> MainActivity::class.java
            false -> AuthorizationActivity::class.java
        }
    }


}
