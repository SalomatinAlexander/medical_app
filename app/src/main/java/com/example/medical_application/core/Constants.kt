package com.example.medical_application.core

import java.util.Date

object Constants {
    val doctorRole = "DocRole"
    val patientRole = "patientRole"

    fun dateIsEquals(date1: Date, date2: Date): Boolean{
        println("Date 1: ${date1}, date2: ${date2}")
        return  date1.equals(date2)
        println("${date1.day}, ${date2.day}," +
                " ${date1.month}, ${date2.month}, ${date1.year}, ${date2.year}")
        return (date1.day == date2.day
            && date1.month == date2.month && date1.year == date2.year)
    }
}