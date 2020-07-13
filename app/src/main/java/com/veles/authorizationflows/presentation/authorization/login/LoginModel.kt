package com.veles.authorizationflows.presentation.authorization.login

import android.content.Intent
import com.google.firebase.auth.FirebaseUser
import com.veles.authorizationflows.domain.email.FirebaseAuthWithEmailAndPassword
import com.veles.authorizationflows.domain.google.FirebaseAuthWithGoogle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class LoginModel @Inject constructor(
    private val firebaseAuthWithEmailAndPassword: FirebaseAuthWithEmailAndPassword,
    private val firebaseAuthWithGoogle: FirebaseAuthWithGoogle
) :
    LoginContract.Model() {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Boolean> {
        return firebaseAuthWithEmailAndPassword.signInWithEmailAndPassword(email, password)
    }

    override suspend fun getSignedInAccountFromIntent(data: Intent?): Flow<Pair<Boolean, FirebaseUser?>> {
        return firebaseAuthWithGoogle
            .getSignedInAccountFromIntent(data)
            .flatMapConcat {
                when(it==null){
                    true->emptyFlow()
                    false->firebaseAuthWithGoogle.apiFirebaseStorage(it.idToken)
                }
        }
    }


}