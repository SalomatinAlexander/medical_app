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
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.view.CreateQuizView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class CreateQuizFragment :MvpAppCompatFragment(), CreateQuizView {
    lateinit var mQuestionRecycler:RecyclerView
    lateinit var mSaveBtn: AppCompatButton
    lateinit var mQuizName:AppCompatEditText
    lateinit var mQuizErrorTxt:TextView
    lateinit var mQuizNoNameError:TextView

    @Inject
    lateinit var mQuizPresenter: CreateQuizPresenter

    @InjectPresenter
    lateinit var quizPresenter: CreateQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): CreateQuizPresenter {
        return   mQuizPresenter
    }

    lateinit var addQuestionBtn:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_quiz, container, false)
   initView(view)
    return  view
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView(view: View){
        view.apply {
            addQuestionBtn = findViewById(R.id.add_question_btn)
            mQuestionRecycler = findViewById(R.id.questions_recycler_view)
            mSaveBtn = findViewById(R.id.save_quiz_btn)
            mQuizName = findViewById(R.id.quiz_name_txt)
            mQuizErrorTxt = findViewById(R.id.quiz_error_txt)
            mQuizNoNameError = findViewById(R.id.quiz_name_error_txt)
        }

        mSaveBtn.setOnClickListener {
            if(mQuizName.text.toString().isEmpty() || mQuizPresenter.quize.quizQuestions.isEmpty()){
              mQuizErrorTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mQuizErrorTxt.visibility = View.GONE
            mQuizPresenter.addQuiz(
                QuizModel(quizName = mQuizName.text.toString(),
                    quizQuestions = mQuizPresenter.quize.quizQuestions)
            )
            MainActivity.INSTANCE.router.backTo(Screens.DocScreen())
        }
        mQuestionRecycler.layoutManager = LinearLayoutManager(this.context,
            RecyclerView.VERTICAL,
            false)

        addQuestionBtn.setOnClickListener {
            if(mQuizName.text.toString().isEmpty()){
                mQuizNoNameError.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mQuizNoNameError.visibility = View.GONE
            MainActivity.INSTANCE.router.navigateTo(Screens.QuestionScreen())
        }

        mQuestionRecycler.adapter = QuestionRecyclerAdapter(mQuizPresenter.quize.quizQuestions)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun newQuestionAdded(question: QuestionModel) {
        println("new")
        mQuestionRecycler.adapter?.notifyDataSetChanged()
    }

    override fun newQuizAdded(quiz: QuizModel) {}
    override fun initQuizList() {}
}