package com.veles.authorizationflows.domain.google

import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthWithGoogleImpl
@Inject constructor(private val firebaseAuth: FirebaseAuth) : FirebaseAuthWithGoogle {

    @ExperimentalCoroutinesApi
    override suspend fun apiFirebaseStorage(idToken: String?): Flow<Pair<Boolean, FirebaseUser?>> {
        return callbackFlow<Pair<Boolean, FirebaseUser?>> {
            if(idToken==null) channel.offer(Pair(false,null))
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
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
    override suspend fun getSignedInAccountFromIntent(data: Intent?): Flow<GoogleSignInAccount?> {
        return callbackFlow<GoogleSignInAccount?> {
            GoogleSignIn.getSignedInAccountFromIntent(data).addOnCompleteListener {
                if(isActive.not()) return@addOnCompleteListener
                channel.offer(it.result)
            }
            awaitClose {
                cancel()
            }
        }
    }

}