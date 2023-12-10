package com.example.medical_application.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizModel

@Dao
interface QuizDAO {

    @get:Query("SELECT * FROM quizdata")
    val all: List<QuizData>?

    @Insert
    fun insert(data: QuizData)
}