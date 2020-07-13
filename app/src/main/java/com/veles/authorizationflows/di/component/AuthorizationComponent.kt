package com.veles.authorizationflows.di.component

import com.veles.authorizationflows.AppApplication

interface AuthorizationComponent {
    fun inject(app: AppApplication)
}