package com.veles.authorizationflows.domain.google

import android.content.Intent
import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthWithGoogle {
    suspend fun apiFirebaseStorage(idToken: String?): Flow<Pair<Boolean, FirebaseUser?>>
    suspend fun getSignedInAccountFromIntent(data: Intent?): Flow<GoogleSignInAccount?>
}