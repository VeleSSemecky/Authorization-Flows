package com.veles.authorizationflows.data.bus.event

import com.veles.authorizationflows.data.bus.event.core.BaseRxBusModel

data class PurchaseModelBus(
    val text:String,
    val count:String
) : BaseRxBusModel() {
}