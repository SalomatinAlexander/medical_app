package com.example.medical_application.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medical_application.data.dao.QuizDAO
import com.example.medical_application.data.dao.UserDAO
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.UserData


@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO?
}