package com.example.medical_application.data.models

import java.util.Objects

data class QuizModel(val quizName:String,
    val quizQuestions:ArrayList<QuestionModel>){

    override fun equals(other: Any?): Boolean {
        if(other is QuizModel){
            return  (other.quizName == quizName && other.quizQuestions == quizQuestions)
        }
        return  false
    }

    override fun hashCode(): Int {
        return quizName.hashCode() + quizQuestions.hashCode()
    }
}


