package com.example.medical_application.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizModel

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface CreateQuizView : MvpView {

    fun newQuestionAdded(question:QuestionModel)
    fun newQuizAdded(quiz:QuizModel)
    fun initQuizList()

    fun initCalendarView(quizList: ArrayList<QuizData>?)
}