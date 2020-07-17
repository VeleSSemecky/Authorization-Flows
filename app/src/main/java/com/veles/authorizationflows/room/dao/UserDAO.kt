package com.veles.authorizationflows.room.dao

import androidx.room.*
import com.veles.authorizationflows.room.model.table.UserTable

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg userModels: UserTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userModel: UserTable): Long

    @Query("SELECT * FROM UserTable")
    suspend fun getUsers(): List<UserTable>

    @Update
    suspend fun update(userModel: UserTable)

    @Delete
    suspend fun delete(userModel: UserTable): Int
}