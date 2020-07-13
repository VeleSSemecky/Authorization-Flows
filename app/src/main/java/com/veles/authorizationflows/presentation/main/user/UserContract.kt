package com.veles.authorizationflows.presentation.main.user

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel
import com.veles.authorizationflows.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserContract {
    interface View : IView {
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract val data: LiveData<FirebaseUser>
        abstract val isProgress: LiveData<Boolean>
        abstract fun getUser()
        abstract fun logout()
    }
    abstract class Model : BaseModel() {
        abstract fun getUser(): FirebaseUser?
        abstract fun logout()
    }
}