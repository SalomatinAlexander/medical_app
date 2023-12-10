package com.example.medical_application.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medical_application.data.dao.AnswerDAO
import com.example.medical_application.data.dao.QuestionDAO
import com.example.medical_application.data.models.AnswerData


@Database(entities = [AnswerData::class], version = 1)
abstract class AnswerDatabase : RoomDatabase() {
    abstract fun answerDao(): AnswerDAO?
}