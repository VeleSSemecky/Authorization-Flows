package com.veles.authorizationflows.presentation.main.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import javax.inject.Inject

class MainViewModel @Inject internal constructor(mainModel: MainModel) :
    MainContract.ViewModel(mainModel) {

    private var isFirstStart = true

    override fun <T : Fragment> openScreen(java: Class<T>): LiveData<Class<T>> {
        val screen = when (isFirstStart) {
            true -> MutableLiveData<Class<T>>(java)
            false -> MutableLiveData()
        }
        isFirstStart = false
        return screen
    }

}
