package com.veles.authorizationflows.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.veles.authorizationflows.domain.email.FirebaseAuthWithEmailAndPassword
import com.veles.authorizationflows.domain.email.FirebaseAuthWithEmailAndPasswordImpl
import com.veles.authorizationflows.domain.google.FirebaseAuthWithGoogle
import com.veles.authorizationflows.domain.google.FirebaseAuthWithGoogleImpl
import com.veles.authorizationflows.domain.update.FirebaseAuthUserUpdate
import com.veles.authorizationflows.domain.update.FirebaseAuthUserUpdateImpl
import com.veles.authorizationflows.domain.weather.WeatherRepository
import com.veles.authorizationflows.domain.weather.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideFirebaseAuthWithGoogle(repository: FirebaseAuthWithGoogleImpl): FirebaseAuthWithGoogle

    @Singleton
    @Binds
    abstract fun provideFirebaseAuthWithEmailAndPassword(repository: FirebaseAuthWithEmailAndPasswordImpl): FirebaseAuthWithEmailAndPassword

    @Singleton
    @Binds
    abstract fun provideFirebaseAuthUserUpdate(repository: FirebaseAuthUserUpdateImpl): FirebaseAuthUserUpdate

    @Singleton
    @Binds
    abstract fun provideWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository

    companion object {
        @Singleton
        @Provides
        fun provideFirebaseDatabase(): FirebaseDatabase = Firebase.database

        @Singleton
        @Provides
        fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

        @Singleton
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
    }
}