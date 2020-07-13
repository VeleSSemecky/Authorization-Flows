package com.veles.authorizationflows.data.bus

import com.veles.authorizationflows.data.bus.event.core.BaseRxBusModel
import kotlinx.coroutines.channels.BroadcastChannel

interface RxBus {
    suspend fun publish(event: BaseRxBusModel)
//    fun listen(eventType: Class<*>): Observable<out BaseRxBusModel>
    fun listen(): BroadcastChannel<BaseRxBusModel>
}