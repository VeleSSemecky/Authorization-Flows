package com.veles.authorizationflows.presentation.main.user

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.domain.email.FirebaseAuthWithEmailAndPassword
import com.veles.authorizationflows.domain.google.FirebaseAuthWithGoogle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class UserModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :
    UserContract.Model() {

    override fun getUser(): FirebaseUser? {
       return firebaseAuth.currentUser
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

}