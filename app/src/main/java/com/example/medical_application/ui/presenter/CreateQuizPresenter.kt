package com.example.medical_application.ui.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.medical_application.data.models.AnswerData
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionData
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.data.repositories.QuizRepository
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.view.CreateQuizView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.random.Random

@InjectViewState
class CreateQuizPresenter: MvpPresenter<CreateQuizView>() {
    val quizRepository = QuizRepository()
    var quize = QuizModel("", arrayListOf())
    var quizList = ArrayList<QuizModel>()

    fun addQuestion(question: QuestionModel){
        quize.quizQuestions.add(question)
        viewState.newQuestionAdded(question)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addQuiz(quiz: QuizModel){
        if(!quizList.contains(quiz)){
            quizList.add(quiz)
            quizList.toSet().toList()
        }
        CoroutineScope(Dispatchers.IO).launch {
            val quizId = LocalDateTime.now().toString().hashCode()+ Random(100000).nextInt()
            for(question:QuestionModel in quiz.quizQuestions){
                val questionId = LocalDateTime.now().toString().hashCode()+ Random(100000).nextInt()
                for(answer:AnswerModel in question.answers){
                    saveAnswer(AnswerData(LocalDateTime.now().toString().hashCode() + Random(1000).nextInt(), answer.answer, answer.points, questionId))
                }
                saveQuestion(QuestionData(questionId, question.question, quizId))
            }
            //saveQuiz(QuizData(quizId, quiz.quizName))

            withContext(Dispatchers.Main){
                if(!quizList.contains(quiz)) {
                    viewState.newQuizAdded(quiz)
                }
            }

        }

    }

    fun getQuizByUserId(userId:String){
       CoroutineScope(Dispatchers.IO).launch {
           val result = quizRepository.getQuizList(userId)
           withContext(Dispatchers.Main){
               viewState.initCalendarView(result)
           }
       }
    }

    fun initQuizList(){
        CoroutineScope(Dispatchers.IO).launch {
            val list = getQuizList()

            withContext(Dispatchers.Main){
                quizList.toSet().toList()
                viewState.initQuizList()
            }
        }
    }

    suspend fun saveQuiz(quiz: QuizData) = coroutineScope {}

    suspend fun saveQuestion(question:QuestionData)= coroutineScope {
        MainActivity.INSTANCE.mQuestionDatabase.questionDao()?.insert(question)
    }
    suspend fun saveAnswer(answer:AnswerData)= coroutineScope {
        MainActivity.INSTANCE.mAnswerDatabase.answerDao()?.insert(answer)
    }

    suspend fun getQuizList() = coroutineScope {}

    suspend fun getQuestionList(quizId:Int) = coroutineScope {
        return@coroutineScope MainActivity.INSTANCE.mQuestionDatabase.questionDao()
            ?.getByQuizId(quizId)
    }

    suspend fun getAnswerList(questionId:Int) = coroutineScope {
        return@coroutineScope MainActivity.INSTANCE.mAnswerDatabase.answerDao()
            ?.getByQuestionId(questionId)
    }


}