package com.veles.authorizationflows.base.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.veles.authorizationflows.base.mvvm.model.IModel

abstract class BaseViewModel<M: IModel> protected constructor(model:M): ViewModel(),
    IViewModel<M> {

    override fun onCleared() {
        super.onCleared()
    }
}
