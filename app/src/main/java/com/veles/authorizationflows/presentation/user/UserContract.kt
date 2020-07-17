package com.veles.authorizationflows.presentation.user

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel

interface UserContract {
    interface View : IView {
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract val data: LiveData<FirebaseUser>
        abstract val isProgress: LiveData<Boolean>
        abstract fun getUser()
        abstract fun logout()
        abstract fun openScreen(): NavDirections?
    }
    abstract class Model : BaseModel() {
        abstract fun getUser(): FirebaseUser?
        abstract fun logout()
        abstract fun isAuthOrNot(): Boolean
    }
}