package com.example.medical_application.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medical_application.data.dao.QuizDAO
import com.example.medical_application.data.models.QuizData


@Database(entities = [QuizData::class], version = 1)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDAO?
}