package com.veles.authorizationflows.data.bus

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class SharedViewModel<T:Any> : ViewModel() {
    abstract fun getShared(): LiveData<T>
    abstract fun setShared(text: T)
}