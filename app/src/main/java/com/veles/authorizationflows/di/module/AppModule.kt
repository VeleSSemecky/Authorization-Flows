package com.veles.authorizationflows.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.veles.authorizationflows.R
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module(subcomponents = [AuthorizedComponent::class])
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun application(_app: Application): Context


    companion object {
        @Provides
        @Singleton
        fun sharedPreferences(_context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(_context)
        }

        @Provides
        @Singleton
        fun googleApiClient(_context: Context): GoogleSignInClient {
            val gso = GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(_context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
            return GoogleSignIn.getClient(_context, gso)
        }

        @Provides
        @Singleton
        fun provideCacheDirectory(_context: Context): File {
            return _context.cacheDir
        }
    }
}