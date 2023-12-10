package com.example.medical_application.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.medical_application.data.models.QuestionData
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizModel

@Dao
interface QuestionDAO {

    @get:Query("SELECT * FROM questiondata")
    val all: List<QuestionData>?

    @Insert
    fun insert(data: QuestionData)

    @Query("SELECT * FROM questiondata WHERE quizId = :id")
    fun getByQuizId(id: Int): List<QuestionData>?
}
