package com.veles.authorizationflows.di

import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.veles.authorizationflows.AppApplication
import com.veles.authorizationflows.base.AppLifecycleObserver
import com.veles.authorizationflows.data.local.data.DataStore
import com.veles.authorizationflows.di.component.AuthorizationComponent
import com.veles.authorizationflows.di.module.AuthorizedComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComponentHandler @Inject internal constructor(
    private val authorizedComponentBuilder: AuthorizedComponent.Builder,
    _context: Context, private val dataStore: DataStore
) {
    private val app: AppApplication = _context.applicationContext as AppApplication

    private var authorizationComponent: AuthorizationComponent? = null

    fun buildRequiredComponent() {
        authorizationComponent = null
        authorizationComponent = authorizedComponentBuilder
            .build()
        authorizationComponent!!.inject(app)

        ProcessLifecycleOwner.get().lifecycle.addObserver(
            AppLifecycleObserver(
                dataStore
            )
        )
    }

}