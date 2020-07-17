package com.veles.authorizationflows.data.bus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SharedViewModelImpl<T:Any> @Inject constructor() : SharedViewModel<T>() {

    private val sharedText = MutableLiveData<T>()

    override fun getShared(): LiveData<T> {
        return sharedText
    }

    override fun setShared(text: T) {
        sharedText.value = text
    }
}