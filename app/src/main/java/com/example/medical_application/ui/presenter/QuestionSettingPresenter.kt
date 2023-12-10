package com.example.medical_application.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.ui.view.CreateQuestionView

@InjectViewState
class QuestionSettingPresenter: MvpPresenter<CreateQuestionView>() {
    var question: QuestionModel = QuestionModel(
        null,
        question = "",
        answers= arrayListOf(),
    )
    fun addAnswer(answer: AnswerModel){
        question.answers.add(answer)
        viewState.addAnswerToRecycler(answer)
    }

    fun saveQuestion(questionTxt: String){
        question = QuestionModel(null, questionTxt, question.answers)
        viewState.addQuestion(question = question)
    }


}