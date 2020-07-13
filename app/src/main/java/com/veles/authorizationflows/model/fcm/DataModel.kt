package com.veles.authorizationflows.model.fcm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(
    val title:String,
    val message:String,
    val tokenSender:String
): Parcelable