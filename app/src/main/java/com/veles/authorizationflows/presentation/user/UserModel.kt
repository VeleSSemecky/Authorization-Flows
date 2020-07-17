package com.veles.authorizationflows.presentation.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :
    UserContract.Model() {
    override fun isAuthOrNot(): Boolean = firebaseAuth.currentUser!=null
    override fun getUser(): FirebaseUser? {
       return firebaseAuth.currentUser
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

}