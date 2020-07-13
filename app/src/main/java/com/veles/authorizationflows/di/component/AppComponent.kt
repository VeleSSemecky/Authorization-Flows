package com.veles.authorizationflows.di.component

import android.app.Application
import com.veles.authorizationflows.AppApplication
import com.veles.authorizationflows.data.bus.RxBusModule
import com.veles.authorizationflows.data.local.DataStoreModule
import com.veles.authorizationflows.data.network.NetworkModule
import com.veles.authorizationflows.data.notification.fcm.NotificationModule
import com.veles.authorizationflows.di.module.AppModule
import com.veles.authorizationflows.di.module.ViewModelProviderFactoryModule
import com.veles.authorizationflows.domain.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelProviderFactoryModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DataStoreModule::class,
        RxBusModule::class,
        NotificationModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(_application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(_app: AppApplication)
}