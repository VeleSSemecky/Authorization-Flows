package com.veles.authorizationflows.data.bus

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RxBusModule {
    @Binds
    @Singleton
    abstract fun provideRxBus(rxBus: RxBusImpl): RxBus
}