package com.veles.authorizationflows.presentation.splash

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : SplashContract.Model() {

    override fun isAuthOrNot(): Boolean = firebaseAuth.currentUser!=null


}