package com.example.medical_application.data.repositories

import com.example.medical_application.data.models.QuizResult
import com.example.medical_application.data.models.UserData
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import java.util.Date

class UserRepository {
    val users: CollectionReference = Firebase.firestore.collection("users")

    suspend fun getUserList(): ArrayList<UserData>? = coroutineScope {
        try{
            var userList: ArrayList<UserData>? = arrayListOf()


            val result =  users.whereIn("role",
                arrayListOf("patientRole"))
                .get().await()
            userList = ArrayList<UserData>();
            for (document in result) {
                userList.add(UserData.UserDataObject.fromJson(document.data))
            }
            return@coroutineScope userList
        }catch (e:Exception){
            return@coroutineScope null
        }
    }

    suspend fun saveUser(user: UserData): Boolean = coroutineScope {
        try{
            val result = users.add(user.toJson()).await()
            return@coroutineScope  true
        }catch (e:Exception){
          return@coroutineScope  false
        }

    }

    suspend fun getCurrentUser(email:String): UserData? = coroutineScope {

        try{
            val result = users.whereIn("email", arrayListOf(email)).limit(1).get().await()
            if(result.documents.isEmpty()){
                return@coroutineScope  null;
            }
            return@coroutineScope result.documents.first().data?.let {
                UserData.UserDataObject.fromJson(it)
            }
        }catch (e:Exception){
            return@coroutineScope  null
        }
    }
}