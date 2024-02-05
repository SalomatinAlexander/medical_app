package com.example.medical_application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.core.Constants
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.view.CreateQuizView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class RoleFragment : MvpAppCompatFragment(), CreateQuizView {
    private lateinit var docBtn:AppCompatButton
    private lateinit var patientBtn:AppCompatButton


    @Inject
    lateinit var mQuizSettingPresenter: CreateQuizPresenter

    @InjectPresenter
    lateinit var quizPresenter: CreateQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): CreateQuizPresenter {
        return   mQuizSettingPresenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        mQuizSettingPresenter.initQuizList()
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_role, container, false)
        initView(view)
    return  view
    }

    private fun initView(view:View){
        docBtn = view.findViewById(R.id.doc_btn)
        patientBtn = view.findViewById(R.id.patient_btn)

        docBtn.setOnClickListener {
            MainActivity.INSTANCE.router.navigateTo(Screens.AuthScreen(Constants.doctorRole))
        }

        patientBtn.setOnClickListener {
            MainActivity.INSTANCE.router.navigateTo(Screens.AuthScreen(Constants.patientRole))
        }

    }

    override fun newQuestionAdded(question: QuestionModel) {}

    override fun newQuizAdded(quiz: QuizModel) {}

    override fun initQuizList() {}
    override fun initCalendarView(quizList: ArrayList<QuizData>?) {

    }
}