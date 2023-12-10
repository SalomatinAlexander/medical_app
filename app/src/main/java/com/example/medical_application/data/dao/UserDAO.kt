package com.example.medical_application.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.medical_application.data.models.QuestionData
import com.example.medical_application.data.models.UserData

@Dao
interface UserDAO {
    @get:Query("SELECT * FROM userdata")
    val all: List<UserData>?

    @Insert
    fun insert(data: UserData)

    @Query("SELECT * FROM userdata WHERE email = :email")
    fun getByEmail(email: String): UserData?
}