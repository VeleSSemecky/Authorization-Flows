package com.veles.authorizationflows.data.bus

import com.veles.authorizationflows.data.bus.event.core.BaseRxBusModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class RxBusImpl @Inject internal constructor() : RxBus {
    override suspend fun publish(event: BaseRxBusModel) {
        channel.send(event)
    }
    override fun listen(): BroadcastChannel<BaseRxBusModel> {
        return channel
    }

    @ExperimentalCoroutinesApi
    companion object {
        val channel = BroadcastChannel<BaseRxBusModel>(1)
    }

}