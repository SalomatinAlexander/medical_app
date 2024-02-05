package com.example.medical_application.ui.doctor_pages.doc

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.data.repositories.UserRepository
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.view.CreateQuizView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import javax.inject.Inject



class DocFragment : MvpAppCompatFragment(), CreateQuizView {
    lateinit var mPatientRecycler:RecyclerView
    val userRepository = UserRepository()


    @Inject
    lateinit var mQuizSettingPresenter:CreateQuizPresenter

    @InjectPresenter
    lateinit var quizPresenter: CreateQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): CreateQuizPresenter{
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
    ): View? {

       val view: View = inflater.inflate(R.layout.fragment_doc, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View){
        view.apply {
            mPatientRecycler = view.findViewById(R.id.patient_list)
        }
        mPatientRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        mPatientRecycler.adapter = PatientRecyclerAdapter(arrayListOf());
        initUserList()
    }

    @SuppressLint("SuspiciousIndentation")
    fun initUserList(){
        CoroutineScope(Dispatchers.IO).launch {
         val userList = userRepository.getUserList()
            withContext(Dispatchers.Main){
                if(userList != null){
                    mPatientRecycler.adapter = PatientRecyclerAdapter(userList);
                }
            }
        }
    }

    override fun newQuestionAdded(question: QuestionModel) {}
    override fun newQuizAdded(quiz: QuizModel) {
        mQuizSettingPresenter.quizList.toSet().toList()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initQuizList() {
        mQuizSettingPresenter.quizList.toSet().toList()
    }

    override fun initCalendarView(quizList: ArrayList<QuizData>?) {}


}