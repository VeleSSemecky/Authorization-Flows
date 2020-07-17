package com.veles.authorizationflows.presentation.change

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ChangeFieldModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :
    ChangeFieldContract.Model() {


}