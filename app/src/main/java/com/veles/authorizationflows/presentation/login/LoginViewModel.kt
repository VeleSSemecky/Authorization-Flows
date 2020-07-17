package com.veles.authorizationflows.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.veles.authorizationflows.common.Keys
import com.veles.authorizationflows.model.user.TypeUser
import com.veles.authorizationflows.model.user.User
import com.veles.authorizationflows.util.isTextValid
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val model: LoginModel,
    private val googleSignInClient: GoogleSignInClient
) : LoginContract.ViewModel(model) {
    override val isProgress: LiveData<Boolean> = MutableLiveData<Boolean>()
    override val dialog: LiveData<String> = MutableLiveData<String>()
    override val userLiveData: LiveData<User> = MutableLiveData<User>()
    override val isAuth: LiveData<Boolean> = MutableLiveData<Boolean>()

    override fun userSocialBundle(user: User): Bundle = Bundle().apply {
        putParcelable(Keys.Args.USER_DATA, user)
    }

    override fun userEmailBundle(): Bundle = Bundle().apply {
        putParcelable(Keys.Args.USER_DATA, User())
    }

    override fun signInWithEmailAndPassword(email: String, password: String) {
        if (email.isTextValid(PatternsCompat.EMAIL_ADDRESS).not()) return
        if (password.isTextValid(Pattern.compile("^.{6,}\$")).not()) return
        (isProgress as MutableLiveData).postValue(true)
        viewModelScope.launch {
            model.signInWithEmailAndPassword(email, password).collect {
                (isAuth as MutableLiveData).postValue(it)
                isProgress.postValue(false)
            }
        }
    }

    override fun getSignedInAccountFromIntent(data: Intent?) {
        (isProgress as MutableLiveData).postValue(true)
        viewModelScope.launch {
            model.getSignedInAccountFromIntent(data).collect {
                isProgress.postValue(false)
                val user = User()
                user.apply {
                    email = it.second?.email ?: ""
                    name = it.second?.displayName ?: ""
                    typeUser = TypeUser.SOCIAL_PROVIDER
                    picture = it.second?.photoUrl
                }
                if ((user.email.isNotEmpty() && user.name.isNotEmpty()))
                    (isAuth as MutableLiveData).postValue(true)
                else
                    (userLiveData as MutableLiveData).postValue(user)
            }
        }
    }


    override fun getGoogleSignInClient(): GoogleSignInClient = googleSignInClient

}