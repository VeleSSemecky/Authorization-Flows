package com.veles.authorizationflows.presentation.main.user

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.common.Keys
import com.veles.authorizationflows.model.user.TypeUser
import com.veles.authorizationflows.model.user.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val model: UserModel
): UserContract.ViewModel(model){

    override val isProgress:LiveData<Boolean> = MutableLiveData<Boolean>()

    override val data:LiveData<FirebaseUser> = MutableLiveData<FirebaseUser>()

    override fun getUser(){
        model.getUser()?.let {
            (data as MutableLiveData).postValue(it)
        }
    }

    override fun logout() {
        model.logout()
    }

}