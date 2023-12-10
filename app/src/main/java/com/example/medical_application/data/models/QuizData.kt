package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizData (
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo("quiz_name") val quizName:String,
)