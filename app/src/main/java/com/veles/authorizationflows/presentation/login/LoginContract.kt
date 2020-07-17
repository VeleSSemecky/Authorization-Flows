package com.veles.authorizationflows.presentation.login

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

interface LoginContract {
    interface View : IView {
    }
    abstract class ViewModel internal constructor(_model: Model) : BaseViewModel<Model>(_model) {
        abstract fun getGoogleSignInClient(): GoogleSignInClient
        abstract fun userSocialBundle(user:User) : Bundle
        abstract fun userEmailBundle() : Bundle
        abstract fun signInWithEmailAndPassword(email:String,password:String)
        abstract fun getSignedInAccountFromIntent(data: Intent?)
        abstract val isAuth: LiveData<Boolean>
        abstract val userLiveData: LiveData<User>
        abstract val isProgress: LiveData<Boolean>

        abstract val dialog: LiveData<String>
    }
    abstract class Model : BaseModel() {
        abstract suspend fun signInWithEmailAndPassword(email:String,password:String): Flow<Boolean>
        abstract suspend fun getSignedInAccountFromIntent(data: Intent?): Flow<Pair<Boolean, FirebaseUser?>>
    }
}