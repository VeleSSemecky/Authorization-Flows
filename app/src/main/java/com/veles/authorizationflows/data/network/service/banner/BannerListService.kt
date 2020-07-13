package com.veles.authorizationflows.data.network.service.banner

import com.veles.authorizationflows.common.Keys
import com.veles.authorizationflows.model.banner.BannerListResponse
import com.veles.authorizationflows.model.core.BaseData
import retrofit2.http.GET
import retrofit2.http.Path

interface BannerListService {
    @GET("/index/banner-list/city_id/" + "{" + Keys.Constant.CITY_ID + "}")
//    @GET
    suspend fun apiBannerList(@Path(Keys.Constant.CITY_ID) cityId: Int): BaseData<List<BannerListResponse>>
}