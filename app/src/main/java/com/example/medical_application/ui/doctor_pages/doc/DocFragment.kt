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
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.view.CreateQuizView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class DocFragment : MvpAppCompatFragment(), CreateQuizView {
    lateinit var mQuizRecycler: RecyclerView
    lateinit var mNoQuizTxt:TextView


    @Inject
    lateinit var mQuizSettingPresenter:CreateQuizPresenter

    @InjectPresenter
    lateinit var quizPresenter: CreateQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): CreateQuizPresenter{
        return   mQuizSettingPresenter
    }

    lateinit var crateQuizBtn: AppCompatButton
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

          crateQuizBtn = findViewById(R.id.create_quiz_btn)
            mQuizRecycler = findViewById(R.id.quiz_recycler)
            mNoQuizTxt = findViewById(R.id.no_quiz_txt)
        }
        mQuizRecycler.layoutManager = LinearLayoutManager(this.context,
            RecyclerView.VERTICAL,
            false)
        mQuizRecycler.adapter = QuizRecyclerAdapter(
            true,
            mQuizSettingPresenter.quizList.toSet().toList())
        //mQuizSettingPresenter.initQuizList()

        crateQuizBtn.setOnClickListener {
            mQuizSettingPresenter.quize = QuizModel("", arrayListOf())
            for (q: QuizModel in mQuizSettingPresenter.quizList){
                if(q.quizName.isEmpty() || q.quizQuestions.isEmpty()){
                    mQuizSettingPresenter.quizList.remove(q)
                }
            }
            mQuizSettingPresenter.quizList.toSet().toList()

            MainActivity.INSTANCE.router.navigateTo(Screens.CreateQuizScreen())
        }

    }

    override fun newQuestionAdded(question: QuestionModel) {}
    override fun newQuizAdded(quiz: QuizModel) {
        mQuizSettingPresenter.quizList.toSet().toList()
        //mQuizRecycler.adapter?.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initQuizList() {
        if(mQuizSettingPresenter.quizList.isNotEmpty()){
            mNoQuizTxt.visibility = View.GONE
        }else{
            mNoQuizTxt.visibility = View.VISIBLE
            return@initQuizList
        }
        mQuizSettingPresenter.quizList.toSet().toList()
        mQuizRecycler.adapter?.notifyDataSetChanged()
    }


}