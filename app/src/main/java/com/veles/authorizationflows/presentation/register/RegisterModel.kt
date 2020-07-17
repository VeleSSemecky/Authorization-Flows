package com.veles.authorizationflows.presentation.register

import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.veles.authorizationflows.domain.email.FirebaseAuthWithEmailAndPassword
import com.veles.authorizationflows.domain.google.FirebaseAuthWithGoogle
import com.veles.authorizationflows.domain.update.FirebaseAuthUserUpdate
import com.veles.authorizationflows.model.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class RegisterModel @Inject constructor(
    private val firebaseAuthWithGoogle: FirebaseAuthWithGoogle,
    private val firebaseAuthWithEmailAndPassword: FirebaseAuthWithEmailAndPassword,
    private val firebaseAuthUserUpdate: FirebaseAuthUserUpdate
) :
    RegisterContract.Model() {

    override suspend fun firebaseUserUpdate(user: User): Flow<Boolean> {
        return firebaseAuthUserUpdate.firebaseUserUpdate(userProfileChangeRequest{
                   displayName = user.name
               })

    }

    override suspend fun createUserWithEmailAndPassword(user: User): Flow<Boolean> {
        return firebaseAuthWithEmailAndPassword.createUserWithEmailAndPassword(user.email, user.pass)
            .flatMapConcat {
                firebaseAuthUserUpdate.firebaseUserUpdate(userProfileChangeRequest{
                    displayName = user.name
                })
            }

    }

    override suspend fun firebaseUserUpdate(profileUpdates: UserProfileChangeRequest): Flow<Boolean> {
        return firebaseAuthUserUpdate.firebaseUserUpdate(profileUpdates)
    }


}