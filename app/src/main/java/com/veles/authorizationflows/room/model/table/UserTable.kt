package com.veles.authorizationflows.room.model.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserTable(
    @PrimaryKey
    val name: String,
    val email: String
){
    override fun toString(): String {
        return "UserTable(name='$name', email='$email')"
    }
}