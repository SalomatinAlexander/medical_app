package com.example.medical_application.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medical_application.data.dao.QuestionDAO
import com.example.medical_application.data.dao.QuizDAO
import com.example.medical_application.data.models.QuestionData


@Database(entities = [QuestionData::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDAO?
}