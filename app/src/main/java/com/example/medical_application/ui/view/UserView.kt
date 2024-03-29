package com.example.medical_application.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.medical_application.data.models.UserData

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface  UserView: MvpView {



}