package com.veles.authorizationflows.base.mvvm.model

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseModel : IModel {

    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(_disposable: Disposable?) {
        compositeDisposable.add(_disposable!!)
    }

    protected fun removeDisposable(_disposable: Disposable?) {
        if (_disposable != null) compositeDisposable.remove(_disposable)
    }

}