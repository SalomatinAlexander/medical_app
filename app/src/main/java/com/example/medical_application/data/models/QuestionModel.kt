package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.Objects

data class QuestionModel(
     val id: Int?,
     val question:String,
     val answers:ArrayList<AnswerModel>
){
     override fun equals(other: Any?): Boolean {
          if(other is QuestionModel){
               return  (other.question == question && other.id == id
                       && other.answers == answers)
          }
          return  false
     }

     override fun hashCode(): Int {
          return  id.hashCode() + question.hashCode() + answers.hashCode()
     }
}

