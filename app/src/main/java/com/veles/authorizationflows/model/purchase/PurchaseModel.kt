package com.veles.authorizationflows.model.purchase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PurchaseModel(
    val createId: String,
    val text: String,
    val count: String,
    var check: Boolean,
    val image: Boolean = false
) : Parcelable {

//    constructor(createId: String, text: String, count: String) : this(createId, text, count, true)

    override fun toString(): String {
        return "PurchaseModel(createId='$createId', text='$text', count='$count', check=$check, image=$image)"
    }
}