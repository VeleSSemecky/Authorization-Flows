package com.veles.authorizationflows.data.network.service.message

import com.veles.authorizationflows.model.fcm.NotificationMessageModel
import retrofit2.Response
import retrofit2.http.*

interface NotificationMessageService {
    @POST
    suspend fun apiNotificationMessage(@Url url:String, @HeaderMap headerMap:Map<String, String>, @Body notificationMassage: NotificationMessageModel): Response<*>
}