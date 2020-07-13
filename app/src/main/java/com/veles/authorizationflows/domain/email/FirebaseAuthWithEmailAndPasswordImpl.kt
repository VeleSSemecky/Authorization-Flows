package com.veles.authorizationflows.domain.email

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthWithEmailAndPasswordImpl
@Inject constructor(private val firebaseAuth: FirebaseAuth) : FirebaseAuthWithEmailAndPassword {

    @ExperimentalCoroutinesApi
    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Pair<Boolean, FirebaseUser?>> {
        firebaseAuth.signOut()
        return callbackFlow<Pair<Boolean, FirebaseUser?>> {
            firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (isActive.not()) return@addOnCompleteListener
                    val user =  when(task.result!=null){
                        true-> task.result!!.user
                        false-> null
                    }
                    channel.offer(Pair(task.isSuccessful,user))
                }
            awaitClose {
                cancel()
            }
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Boolean> {
        firebaseAuth.signOut()
        return callbackFlow<Boolean> {
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (isActive.not()) return@addOnCompleteListener
                    channel.offer(task.isSuccessful)
                }
            awaitClose {
                cancel()
            }
        }
    }

}