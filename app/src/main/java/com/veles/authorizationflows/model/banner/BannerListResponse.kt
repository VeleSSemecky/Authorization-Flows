package com.veles.authorizationflows.model.banner

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.veles.authorizationflows.model.core.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BannerListResponse(
    @SerializedName("banner_id")
    @Expose
    var bannerId: Int? = null,
    @SerializedName("banner_name")
    @Expose
    var bannerName: String? = null,
    @SerializedName("icon_url")
    @Expose
    var iconUrl: String? = null,
    @SerializedName("site_url")
    @Expose
    var siteUrl: String? = null
) : Model(), Parcelable