package com.veles.authorizationflows.presentation.main.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel

interface MainContract {
    interface View : IView {
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract fun <T : Fragment> openScreen(java: Class<T>):LiveData<Class<T>>
    }
    abstract class Model : BaseModel() {

    }
}