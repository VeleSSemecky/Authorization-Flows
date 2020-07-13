package com.veles.authorizationflows.model.fcm

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationMessageModel(
    @SerializedName("to")
    @Expose
    var to: String = "/topics/all",
    @SerializedName("data")
    @Expose
    var data: DataModel
): Parcelable
