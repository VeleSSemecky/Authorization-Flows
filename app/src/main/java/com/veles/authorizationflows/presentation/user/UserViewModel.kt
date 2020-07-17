package com.veles.authorizationflows.presentation.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.room.dao.UserDAO
import com.veles.authorizationflows.room.model.table.UserTable
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val model: UserModel,
    private val userDAO: UserDAO
): UserContract.ViewModel(model){

    override val isProgress:LiveData<Boolean> = MutableLiveData<Boolean>()

    override val data:LiveData<FirebaseUser> = MutableLiveData<FirebaseUser>()

    override fun getUser(){
        model.getUser()?.let {
            (data as MutableLiveData).postValue(it)
            viewModelScope.launch {
                userDAO.insert(UserTable(it.displayName!!, it.email!!))
                userDAO.getUsers().forEach {
                    Log.i("getUser", "getUser: $it")
                }
            }

        }
    }

    override fun logout() {
        model.logout()
    }
    override fun openScreen(): NavDirections? {
        return when (model.isAuthOrNot()) {
            true -> null
            false -> UserFragmentDirections.main()
        }
    }
}