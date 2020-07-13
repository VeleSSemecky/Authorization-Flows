package com.veles.authorizationflows.model.core

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseData<T>() : Model(), Parcelable {
    @SerializedName("data")
    @Expose
    var data: T? = null
        private set

    @SerializedName("error")
    @Expose
    var error: Error?=null

    @SerializedName("response")
    @Expose
    var response: Boolean?=null

    constructor(parcel: Parcel) : this() {
        error = parcel.readParcelable(Error::class.java.classLoader)
        response = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(error, flags)
        parcel.writeValue(response)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseData<Any>> {
        override fun createFromParcel(parcel: Parcel): BaseData<Any> {
            return BaseData(parcel)
        }

        override fun newArray(size: Int): Array<BaseData<Any>?> {
            return arrayOfNulls(size)
        }
    }

}