package com.example.medical_application.data.repositories

import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizResult
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import java.util.Date

class QuizRepository {
    val quiz = Firebase.firestore.collection("quiz")

     suspend fun getQuizList(userId:String): ArrayList<QuizData>? = coroutineScope{
        try{
            println("USER ID IS ${userId}")
            val result = quiz.whereIn("user_id", arrayListOf(userId)).get().await()
            var quizList:ArrayList<QuizData> = arrayListOf();
            for(document in result){
                quizList.add(QuizData.QuizDataObject.fromJson(document.data))
            }
            return@coroutineScope  quizList
        }catch (e:Exception){
            return@coroutineScope null
        }
    }

    suspend fun saveQuiz(quizData: QuizData): Boolean = coroutineScope {
        try{
            println("SAVE QUIZ: ${quizData}\n\n\n\n")
            val result = quiz.add(quizData.toJson()).await()
            return@coroutineScope true
        }catch (e: Exception){

            return@coroutineScope  false
        }
    }
}