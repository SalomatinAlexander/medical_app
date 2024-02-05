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
import com.example.medical_application.data.models.UserAnswerData
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.view.UserQuizView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.random.Random


@InjectViewState
class UserQuizPresenter : MvpPresenter<UserQuizView>(){
    var quizList = ArrayList<QuizModel>()

    fun initQuizList(){
        CoroutineScope(Dispatchers.IO).launch {
            val list = getQuizList()

           /*
            if (list != null) {
                for (quiz: QuizData in list) {
                    val mQuestionList:ArrayList<QuestionModel> = arrayListOf()
                    if(quiz.uuid == null) return@launch
                    val questionsList =  getQuestionList(quiz.uuid)
                    if (questionsList != null) {
                        for(question: QuestionData in questionsList){
                            val mAnswerList:ArrayList<AnswerModel> = arrayListOf()
                            val answerList = question.id?.let { getAnswerList(it) }
                            if (answerList != null) {
                                for(a: AnswerData in answerList){
                                    mAnswerList.add(
                                        AnswerModel(answer = a.answer,
                                            points = a.points)
                                    )
                                }
                            }
                            mQuestionList.add(
                                QuestionModel(id = question.id, question = question.question,
                                    answers = mAnswerList)
                            )
                        }

                        val model = QuizModel(quiz., quizQuestions = mQuestionList)
                        if(!quizList.contains(model)) {
                            quizList.add(QuizModel(quiz.quizName, quizQuestions = mQuestionList))
                        }
                    }
                }
            }
            */
            withContext(Dispatchers.Main){
                println("IN LIST USER QUIZ")
                quizList.toSet().toList()
                viewState.initQuizList()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveAnswers(answers:List<AnswerModel>, userId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            saveUserAnswers(answers, userId)
            withContext(Dispatchers.Main) {
                viewState.answersSaved()
            }
        }
    }
    fun getAnswersPoint(userId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val points = getAnswerPoint(userId)
            withContext(Dispatchers.Main) {
                viewState.setUserPoint(points)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun saveUserAnswers(answers:List<AnswerModel>, userId:Int) = coroutineScope {
     println("IN SAVE ANSWER:${answers.size}")
        MainActivity.INSTANCE.mUserAnswerDatabse.clearAllTables()
      for(answer: AnswerModel in answers){
          println("SAVE ANSWER: ${answer.answer}, ${answer.points}")

          MainActivity.INSTANCE.mUserAnswerDatabse.userAnswerDao()?.insert(
              UserAnswerData(
                  LocalDateTime.now().toString().hashCode()+ Random(100000).nextInt(),
                  answer.answer, answer.points, userId)
          )
          val answersList = MainActivity.INSTANCE.mUserAnswerDatabse.userAnswerDao()?.getByUserId(userId)
      println("ANSWER IN SAVE:${answersList?.size}")
      }

    }

    suspend fun getAnswerPoint(userId: Int) = coroutineScope {
        val answers = MainActivity.INSTANCE.mUserAnswerDatabse.userAnswerDao()?.getByUserId(userId)
        println("IN GET ANSWER:${answers?.size}")
        if(!answers.isNullOrEmpty()){
            var points = 0
            for(a:UserAnswerData in answers){
                points += a.points
                println("answer: ANSWER: ${a.answer}, AP:${a.points}, POINTS:${points}")
            }
            return@coroutineScope points
        }
        return@coroutineScope 0
    }

    suspend fun getQuizList() = coroutineScope {

    }

    suspend fun getQuestionList(quizId:Int) = coroutineScope {
        return@coroutineScope MainActivity.INSTANCE.mQuestionDatabase.questionDao()
            ?.getByQuizId(quizId)
    }

    suspend fun getAnswerList(questionId:Int) = coroutineScope {
        return@coroutineScope MainActivity.INSTANCE.mAnswerDatabase.answerDao()
            ?.getByQuestionId(questionId)
    }
}