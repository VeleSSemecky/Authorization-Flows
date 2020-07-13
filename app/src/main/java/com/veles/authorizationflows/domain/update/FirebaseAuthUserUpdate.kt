package com.veles.authorizationflows.domain.update

import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthUserUpdate {
    suspend fun firebaseUserUpdate(profileUpdates: UserProfileChangeRequest):Flow<Boolean>
}