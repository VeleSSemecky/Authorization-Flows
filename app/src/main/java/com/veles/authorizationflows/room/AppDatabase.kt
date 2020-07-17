package com.veles.authorizationflows.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.veles.authorizationflows.room.dao.UserDAO
import com.veles.authorizationflows.room.model.table.UserTable
import javax.inject.Singleton

@Database(entities = [UserTable::class], version = 1, exportSchema = false)
@Singleton
abstract class AppDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
}