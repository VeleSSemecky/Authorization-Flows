package com.veles.authorizationflows.domain.update

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthUserUpdateImpl
@Inject constructor(private val firebaseAuth: FirebaseAuth): FirebaseAuthUserUpdate {

    @ExperimentalCoroutinesApi
    override suspend fun firebaseUserUpdate(profileUpdates: UserProfileChangeRequest): Flow<Boolean> {
        return callbackFlow<Boolean> {
            firebaseAuth.currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener{
                if(isActive.not()) return@addOnCompleteListener
                channel.offer(it.isSuccessful)
            }
            awaitClose {
                cancel()
            }
        }
    }
}