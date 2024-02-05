package com.example.medical_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



data class UserData(
    val userId: String,
    val email:String,
    val name:String,
    val soname:String,
    val role:String,
){

    override fun toString(): String {
        return  "UserData(userId:$userId, email:$email, name:$name, soname:$soname, tole:$role)"
    }

    fun toJson(): Map<String, Any>{
     return  mapOf("user_id" to userId, "email" to email, "name" to name, "soname" to soname, "role" to role)
    }



    object UserDataObject{
        fun fromJson(json: Map<String, Any>): UserData {
            return UserData(
                userId = json["user_id"]  as String,
                email = json["email"] as String,
                name = json["name"] as String,
                soname = json["soname"] as String,
                role = json["role"] as String,
            )
        }
    }

    /*
    [1,2,4,5]
     */
    /*
    {
    "a": "srhfhjs",
    "b": 2,
    }
     */
}