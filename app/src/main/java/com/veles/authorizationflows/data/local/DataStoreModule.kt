package com.veles.authorizationflows.data.local

import com.veles.authorizationflows.data.local.data.DataStore
import com.veles.authorizationflows.data.local.data.DataStoreImpl
import com.veles.authorizationflows.data.local.permission.PermissionManager
import com.veles.authorizationflows.data.local.permission.PermissionManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataStoreModule {
    @Binds
    @Singleton
    abstract fun provideDataStore(_dataStore: DataStoreImpl): DataStore

    @Binds
    @Singleton
    abstract fun providePermissionManager(_dataStore: PermissionManagerImpl): PermissionManager
}