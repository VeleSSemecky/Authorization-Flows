package com.veles.authorizationflows.domain.email

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthWithEmailAndPassword {
    suspend fun createUserWithEmailAndPassword(email:String,password:String): Flow<Pair<Boolean, FirebaseUser?>>
    suspend fun signInWithEmailAndPassword(email:String,password:String): Flow<Boolean>
}