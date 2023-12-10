package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAnswerData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo("answer") val answer:String,
    @ColumnInfo("points") val points: Int,
    @ColumnInfo("userId") val userId: Int
)