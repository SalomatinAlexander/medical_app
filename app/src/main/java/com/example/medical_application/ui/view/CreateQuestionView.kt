package com.example.medical_application.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizData

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface CreateQuestionView : MvpView {
    fun addAnswerToRecycler(answer: AnswerModel)

    fun addQuestion(question:QuestionModel)

}