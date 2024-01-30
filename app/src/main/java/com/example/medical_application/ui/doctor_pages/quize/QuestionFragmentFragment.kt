package com.example.medical_application.ui.doctor_pages.quize

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.ui.doctor_pages.quize.asnwer.AnswerRecyclerAdapter
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.presenter.QuestionSettingPresenter
import com.example.medical_application.ui.view.CreateQuestionView
import com.example.medical_application.ui.view.CreateQuizView
import moxy.MvpAppCompatFragment
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.random.Random


class QuestionFragmentFragment : MvpAppCompatFragment(),
    CreateQuestionView, CreateQuizView {
    lateinit var addAnswer:AppCompatButton
    lateinit var mAnswerSettingRecycler: RecyclerView
    lateinit var mErrorTxt: TextView
    lateinit var mSaveBtn: AppCompatButton
    lateinit var mQuestionTxt:AppCompatEditText
    lateinit var mNoNameTxt:TextView

    @Inject
    lateinit var mQuizSettingPresenter:CreateQuizPresenter

    @InjectPresenter
    lateinit var quizPresenter: CreateQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): CreateQuizPresenter{
        return   mQuizSettingPresenter
    }

    @Inject
    lateinit var mQuestionPresenter: QuestionSettingPresenter

    @InjectPresenter
    lateinit var questionPresenter: QuestionSettingPresenter

    @ProvidePresenter
    fun provideLogoutPresenter(): QuestionSettingPresenter {
        return   mQuestionPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        mQuestionPresenter.question = QuestionModel(null,
        question = "",
        answers= arrayListOf())
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_fragment, container, false)
        initView(view)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView(view: View){
        view.apply {
            addAnswer = findViewById(R.id.add_answer_btn)
            mAnswerSettingRecycler = findViewById(R.id.answer_recycler_setting_id)
            mErrorTxt = findViewById(R.id.question_error_txt)
            mSaveBtn = findViewById(R.id.save_question_btn)
            mQuestionTxt = findViewById(R.id.question_text_id)
            mNoNameTxt = findViewById(R.id.question_name_error_txt)
        }
        mSaveBtn.setOnClickListener {
            if(mQuestionTxt.text.toString().isEmpty() || mQuestionPresenter.question.answers.isEmpty()){
                mErrorTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mErrorTxt.visibility = View.GONE
            println("Add question 22")
            mQuizSettingPresenter.addQuestion(
                QuestionModel(question = mQuestionTxt.text.toString(),
                    answers = mQuestionPresenter.question.answers, id = LocalDateTime.now().hashCode() + Random(10000).nextInt()
                )
            )
           // mQuestionPresenter.saveQuestion(mQuestionTxt.text.toString())
            MainActivity.INSTANCE.router.backTo(Screens.CreateQuizScreen())


        }



        addAnswer.setOnClickListener {
            if(mQuestionTxt.text.toString().isEmpty()){
                mNoNameTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mNoNameTxt.visibility = View.GONE
            MainActivity.INSTANCE.router.navigateTo(Screens.AnswerSettingScreen())
        }
        mAnswerSettingRecycler.layoutManager = LinearLayoutManager(this.context,
            RecyclerView.VERTICAL,
            false)
        mAnswerSettingRecycler.adapter = AnswerRecyclerAdapter(mQuestionPresenter.question.answers)

    }



    @SuppressLint("NotifyDataSetChanged")
    override fun addAnswerToRecycler(answer: AnswerModel) {
        mAnswerSettingRecycler.adapter?.notifyDataSetChanged();

    }

    override fun addQuestion(question: QuestionModel) {

    }

    override fun newQuestionAdded(question: QuestionModel) {

    }

    override fun newQuizAdded(quiz: QuizModel) {}
    override fun initQuizList() {}


}