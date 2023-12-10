package com.example.medical_application.di



import com.example.medical_application.RoleFragment
import com.example.medical_application.ui.doctor_pages.quize.asnwer.AnswerSettingFragment
import com.example.medical_application.ui.doctor_pages.quize.CreateQuizFragment
import com.example.medical_application.ui.doctor_pages.quize.QuestionFragmentFragment
import com.example.medical_application.ui.doctor_pages.doc.DocFragment
import com.example.medical_application.ui.patient_pages.auth.AuthFragment
import com.example.medical_application.ui.patient_pages.patient.PatientFragment
import com.example.medical_application.ui.patient_pages.patient.QuizFragment
import dagger.Component
import javax.inject.Singleton



@Component(modules = [ProviderModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: AnswerSettingFragment)
    fun inject(fragment: QuestionFragmentFragment)
    fun inject(fragment: CreateQuizFragment)
    fun inject(fragment: DocFragment)
    fun inject(fragment: PatientFragment)
    fun inject(fragment: QuizFragment)
    fun inject(fragment: AuthFragment)
    fun inject(fragment: RoleFragment)





}
