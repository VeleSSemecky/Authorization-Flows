package com.veles.authorizationflows.presentation.activity

import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel

interface MainContract {
    interface View : IView {
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
    }
    abstract class Model : BaseModel() {
    }
}