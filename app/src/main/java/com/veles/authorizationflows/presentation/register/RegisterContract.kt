package com.veles.authorizationflows.presentation.register

import androidx.lifecycle.LiveData
import com.google.firebase.auth.UserProfileChangeRequest
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.model.BaseModel
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel
import com.veles.authorizationflows.model.user.User
import kotlinx.coroutines.flow.Flow

interface RegisterContract {
    interface View : IView {
        fun createUser(): User
        fun onRegistration(view: android.view.View)
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract fun firebaseUserUpdate(user: User)
        abstract fun createUserWithEmailAndPassword(user:User)
        abstract val data: LiveData<Boolean>
        abstract val isProgress: LiveData<Boolean>
    }
    abstract class Model : BaseModel() {
        abstract suspend fun firebaseUserUpdate(user: User): Flow<Boolean>
        abstract suspend fun createUserWithEmailAndPassword(user:User): Flow<Boolean>
        abstract suspend fun firebaseUserUpdate(profileUpdates: UserProfileChangeRequest):Flow<Boolean>
    }
}