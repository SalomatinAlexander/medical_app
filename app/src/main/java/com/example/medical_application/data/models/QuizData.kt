package com.example.medical_application.data.models

import com.google.firebase.Timestamp


data class QuizData(
    val answers: List<String>?,
    val createAt: Timestamp,
    val userId: String,
    val uuid: String,

    ){
    override fun toString(): String {
        return  "QuizData(answers:$answers, createAt:$createAt, userId:$userId, uuid:uuid)"
    }

    fun toJson(): Map<String, Any?> {
        return mapOf("answers" to answers, "create_at" to createAt,  "user_id" to userId, "uuid" to uuid)
    }

    object QuizDataObject{

        fun fromJson(json:Map<String, Any>): QuizData{
            return QuizData(
                answers = json["answers"] as List<String>?,
                createAt = json["create_at"] as Timestamp,
                userId = json["user_id"] as String,
                uuid = json["uuid"] as String)

        }
    }
}