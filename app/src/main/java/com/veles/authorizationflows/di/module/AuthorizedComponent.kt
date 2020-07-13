package com.veles.authorizationflows.di.module

import com.veles.authorizationflows.di.component.AuthorizationComponent
import com.veles.authorizationflows.di.scope.AuthorizedScope
import dagger.Subcomponent

@AuthorizedScope
@Subcomponent(modules = [AuthorizedModule::class])
interface AuthorizedComponent : AuthorizationComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): AuthorizedComponent?
    }
}