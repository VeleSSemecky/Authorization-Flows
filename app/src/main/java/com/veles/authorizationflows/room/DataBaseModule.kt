package com.veles.authorizationflows.room

import android.content.Context
import androidx.room.Room
import com.veles.authorizationflows.BuildConfig
import com.veles.authorizationflows.room.dao.UserDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDAO(database: AppDatabase): UserDAO {
        return database.userDAO
    }
}