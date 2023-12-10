package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionData (
    @PrimaryKey val id: Int,
    @ColumnInfo("question") val question:String,
    @ColumnInfo("quizId") val quizId: Int
)