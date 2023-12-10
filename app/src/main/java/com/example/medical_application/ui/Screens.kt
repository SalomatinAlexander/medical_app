package com.example.medical_application.ui

import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.ui.doctor_pages.quize.asnwer.AnswerSettingFragment
import com.example.medical_application.ui.doctor_pages.quize.CreateQuizFragment
import com.example.medical_application.ui.doctor_pages.doc.DocFragment
import com.example.medical_application.ui.doctor_pages.quize.QuestionFragmentFragment
import com.example.medical_application.ui.patient_pages.auth.AuthFragment
import com.example.medical_application.ui.patient_pages.patient.PatientFragment
import com.example.medical_application.ui.patient_pages.patient.QuizFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {
    fun AnswerSettingScreen() = FragmentScreen { AnswerSettingFragment() }
    fun CreateQuizScreen() = FragmentScreen { CreateQuizFragment() }
    fun DocScreen() = FragmentScreen { DocFragment() }
    fun QuestionScreen() = FragmentScreen { QuestionFragmentFragment() }
    fun AuthScreen() = FragmentScreen { AuthFragment() }
    fun QuizScreen(quiz:QuizModel) = FragmentScreen { QuizFragment(quiz) }
    fun PatientScreen() = FragmentScreen { PatientFragment() }
    //fun TrainScreen() = FragmentScreen{ TrainFragment()}
    // fun SeaScreen() = FragmentScreen{SeaFragment()}

}