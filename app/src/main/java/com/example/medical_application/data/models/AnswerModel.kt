package com.example.medical_application.data.models

data class AnswerModel(
    val answer:String,
    val points: Int
){
    override fun equals(other: Any?): Boolean {
        if(other is AnswerModel){
            return  (other.answer == answer && other.points == points)
        }
        return  false
    }

    override fun hashCode(): Int {
        return answer.hashCode() + points.hashCode()
    }
}