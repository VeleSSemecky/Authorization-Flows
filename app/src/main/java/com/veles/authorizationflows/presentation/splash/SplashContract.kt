package com.veles.authorizationflows.presentation.splash

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel

interface SplashContract {
    interface View : IView {
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract fun <T : Fragment> openScreen(java: Class<T>):LiveData<Class<T>>
        abstract fun openScreen(): Class<out Activity>
    }
    abstract class Model : BaseModel() {
        abstract fun isAuthOrNot():Boolean
    }
}