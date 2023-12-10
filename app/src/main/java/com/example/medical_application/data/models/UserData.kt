package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo("email") val email:String,
    @ColumnInfo("password") val password: String,

    //User
    // email password
    // sasha@gmail.com 1234
    // dima@gmail.com 12345
    //

)