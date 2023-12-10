package com.example.medical_application.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.medical_application.data.models.AnswerData
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionData
import com.example.medical_application.data.models.QuestionModel

@Dao
interface AnswerDAO {

    @get:Query("SELECT * FROM answerdata")
    val all: List<AnswerData>?

    @Insert
    fun insert(data: AnswerData)

    @Query("SELECT * FROM answerdata WHERE questionId = :id")
    fun getByQuestionId(id: Int): List<AnswerData>?
}