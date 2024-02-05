package com.example.medical_application.data.models

import java.util.Date

data class QuizResult(
    val answers: List<String>,
    val userId: String,
    val uuid: String,
    val createAt: Date
){
    override fun toString(): String {
        return "QuizResult(answer: $answers, userId: $userId, uuid: $uuid, createAt: $createAt)"
    }

}


