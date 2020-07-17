package com.veles.authorizationflows.presentation.register

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.veles.authorizationflows.model.user.User
import com.veles.authorizationflows.util.isTextValid
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val model: RegisterModel
): RegisterContract.ViewModel(model){
    override val data:LiveData<Boolean> = MutableLiveData<Boolean>()
    override val isProgress:LiveData<Boolean> = MutableLiveData<Boolean>()

    override fun firebaseUserUpdate(user: User){
        (isProgress as MutableLiveData).postValue(true)
        viewModelScope.launch {
            model.firebaseUserUpdate(user).collect {
                (data as MutableLiveData).postValue(it)
                isProgress.postValue(false)
            }
        }
    }

    override fun createUserWithEmailAndPassword(user:User) {
        if(user.email.isTextValid(PatternsCompat.EMAIL_ADDRESS).not()) return
        if(user.pass.isTextValid(Pattern.compile("^.{6,}\$")).not()) return
        (isProgress as MutableLiveData).postValue(true)
        viewModelScope.launch {
            model.createUserWithEmailAndPassword(user).collect {
                (data as MutableLiveData).postValue(it)
                isProgress.postValue(false)
            }
        }
    }

}