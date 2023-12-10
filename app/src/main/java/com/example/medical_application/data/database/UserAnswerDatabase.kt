package com.example.medical_application.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medical_application.data.dao.QuizDAO
import com.example.medical_application.data.dao.UserAnswerDAO
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.UserAnswerData


@Database(entities = [UserAnswerData::class], version = 1)
abstract class UserAnswerDatabase : RoomDatabase() {
    abstract fun userAnswerDao(): UserAnswerDAO?
}