package com.example.medical_application.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.medical_application.data.database.UserAnswerDatabase
import com.example.medical_application.data.models.AnswerData
import com.example.medical_application.data.models.QuestionData
import com.example.medical_application.data.models.UserAnswerData
@Dao
interface UserAnswerDAO {

    @Insert
    fun insert(data: UserAnswerData)

    @Query("SELECT * FROM useranswerdata WHERE userId = :userId")
    fun getByUserId(userId: Int): List<UserAnswerData>?


}