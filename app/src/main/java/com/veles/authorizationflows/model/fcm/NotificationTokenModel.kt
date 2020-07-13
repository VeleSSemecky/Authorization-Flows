package com.veles.authorizationflows.model.fcm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationTokenModel(val token: String) : Parcelable