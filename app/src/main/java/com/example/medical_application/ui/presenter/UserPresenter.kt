package com.example.medical_application.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.medical_application.data.models.UserData
import com.example.medical_application.data.repositories.UserRepository
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.view.CreateQuestionView
import com.example.medical_application.ui.view.UserView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@InjectViewState
class UserPresenter : MvpPresenter<UserView>() {
    val userRepository = UserRepository()
    var currentUser:UserData? = null


}