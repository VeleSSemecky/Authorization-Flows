package com.veles.authorizationflows.presentation.authorization.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import javax.inject.Inject

class AuthorizationViewModel @Inject internal constructor(authorizationModel: AuthorizationModel) :
    AuthorizationContract.ViewModel(authorizationModel) {

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
