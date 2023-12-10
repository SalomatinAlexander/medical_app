package com.example.medical_application.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface UserQuizView: MvpView {
    fun initQuizList()
    fun answersSaved()
    fun setUserPoint(points:Int)
}