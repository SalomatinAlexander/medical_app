package com.example.medical_application.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.medical_application.data.models.UserData
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
    var currentUser:UserData? = null

    fun authUser(email:String, password:String){
        CoroutineScope(Dispatchers.IO).launch {
            val user = getUser(email)
            if(user != null){
                currentUser = user
                withContext(Dispatchers.Main){
                    viewState.userIsAuth()
                }
            }else{
                insertUser(UserData(null, email, password))
                val user = getUser(email)
                currentUser = user
                withContext(Dispatchers.Main){
                    viewState.userIsAuth()
                }

            }
        }

    }

    suspend fun getUser(email: String)= coroutineScope {
        return@coroutineScope MainActivity.INSTANCE.mUserDatabase.userDao()?.getByEmail(email)
    }

    suspend fun insertUser(user:UserData) = coroutineScope {
        MainActivity.INSTANCE.mUserDatabase.userDao()?.insert(user)
    }
}