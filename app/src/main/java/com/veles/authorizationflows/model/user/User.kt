package com.veles.authorizationflows.model.user

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String ="",
    var email: String ="",
    var pass: String ="",
    var socialToken: String ="",
    var picture: Uri? = null,
    var typeUser: TypeUser = TypeUser.EMAIL_PASS
): Parcelable