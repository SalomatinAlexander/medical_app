package com.example.medical_application.ui.patient_pages.patient

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.core.Constants
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.data.models.UserData
import com.example.medical_application.data.repositories.QuizRepository
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.view.CreateQuizView
import com.google.type.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject


class PatientFragment constructor(user: UserData,
                                  isDoctor: Boolean) :  MvpAppCompatFragment(), CreateQuizView {
    val currentUser: UserData = user
    val userIsDoctor : Boolean = isDoctor
    var userQuizList:ArrayList<QuizData> = arrayListOf();
    val quizRepository: QuizRepository = QuizRepository()
    lateinit var addAnswerBtn: AppCompatButton
    lateinit var mDefaultTxt:TextView

    lateinit var mCalender: CalendarView
    lateinit var mDateTxt: TextView

    @Inject
    lateinit var mQuizSettingPresenter: CreateQuizPresenter

    @InjectPresenter
    lateinit var quizPresenter: CreateQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): CreateQuizPresenter {
        return mQuizSettingPresenter
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)

        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_patient, container, false)
        initView(view)
        mQuizSettingPresenter
            .getQuizByUserId(userId = currentUser.userId)
        return view
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun initView(view: View) {
        view.apply {
            mDefaultTxt = findViewById(R.id.defaultTextView)
            mCalender = findViewById(R.id.calendarView)
            mDateTxt = findViewById(R.id.DateTextView)
            addAnswerBtn = findViewById(R.id.add_quiz_answer_btn)
        }
        getQuizData()
        if(userIsDoctor){
            addAnswerBtn.visibility = View.GONE
        }else{
            addAnswerBtn.setOnClickListener{
                MainActivity.INSTANCE.router.navigateTo(Screens.AddNoteScreen(user = currentUser))
            }
        }



        mCalender.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
           checkDate(date)
            mDateTxt.text = date
        }


    }

    private fun checkDate(date:String){
        for(quiz in userQuizList){
            if(date == SimpleDateFormat("d/M/yyyy").format(quiz.createAt.toDate())){
                var answers: String = ""
                if(quiz.answers == null) return
                for(a in quiz.answers){
                    answers += "\n$a"
                }

                mDefaultTxt.text = answers
            }
        }
    }

    private fun getQuizData(){
        println("GET QUIZ DATA")
        CoroutineScope(Dispatchers.IO).launch {
            println("GET QUIZ DATA LAUNCH: ${currentUser.userId}")
            val quizList =  quizRepository
                .getQuizList(userId = currentUser.userId)
            withContext(Dispatchers.Main){
                println("GET QUIZ DATA: list${quizList?.size}")
               initCalendarView(quizList)
            }

        }
    }

    override fun newQuestionAdded(question: QuestionModel) {}

    override fun newQuizAdded(quiz: QuizModel) {}

    override fun initQuizList() {}

    override fun initCalendarView(quizList: ArrayList<QuizData>?) {
        if(quizList != null){
            userQuizList = quizList
            val currentDate = Date(mCalender.date)
            checkDate(SimpleDateFormat("d/M/yyyy").format(currentDate))
            //Инициализируем данные
        }else{
            //Выводим текст об ошибке
        }

    }

}