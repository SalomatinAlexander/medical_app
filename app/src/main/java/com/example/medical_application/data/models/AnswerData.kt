package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnswerData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("answer") val answer:String,
    @ColumnInfo("points") val points: Int,
    @ColumnInfo("questionId") val questionId: Int
)
