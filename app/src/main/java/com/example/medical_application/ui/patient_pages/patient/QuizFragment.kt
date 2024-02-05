package com.example.medical_application.ui.patient_pages.patient

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
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.data.models.UserData
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.presenter.UserPresenter
import com.example.medical_application.ui.presenter.UserQuizPresenter
import com.example.medical_application.ui.view.CreateQuizView
import com.example.medical_application.ui.view.UserQuizView
import com.example.medical_application.ui.view.UserView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class QuizFragment(val quiz:QuizModel) : MvpAppCompatFragment(), UserQuizView, UserView {
    lateinit var mFinishTestBtn:AppCompatButton
    lateinit var mCanSaveTxt:TextView

    val answerList:ArrayList<AnswerModel> = arrayListOf()
    val checkAnswerList:ArrayList<AnswerModel> = arrayListOf()



    @Inject
    lateinit var mPresenter: UserQuizPresenter

    @InjectPresenter
    lateinit var userQuizPresenter: UserQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): UserQuizPresenter {
        return   mPresenter
    }

    @Inject
    lateinit var mUserPresenter: UserPresenter

    @InjectPresenter
    lateinit var userPresenter: UserPresenter

    @ProvidePresenter
    fun provideUserPresenter(): UserPresenter {
        return  mUserPresenter
    }

    lateinit var mQuizRecycler:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        PatientQuizRecyclerAdapter.answerCount = -1;
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        initView(view)
        return  view
    }

    fun initView(view: View){
        view.apply {
            mQuizRecycler = findViewById(R.id.patient_quiz_recycler)
            mFinishTestBtn = findViewById(R.id.finish_test_btn)
            mCanSaveTxt = findViewById(R.id.quiz_can_save)
        }

           if(answerList.isEmpty()){
               for(question:QuestionModel in quiz.quizQuestions){
                   for(a:AnswerModel in question.answers){
                       answerList.add(a)
                   }
               }
           }



        mFinishTestBtn.setOnClickListener {

            if(checkAnswerList.size == quiz.quizQuestions.size){
                mCanSaveTxt.visibility = View.GONE
                saveAnswers()

            }else {
                mCanSaveTxt.visibility = View.VISIBLE
            }
        }
        mQuizRecycler.layoutManager =  LinearLayoutManager(this.context,
        RecyclerView.VERTICAL,
        false)
        mQuizRecycler.adapter = PatientQuizRecyclerAdapter(this, quiz.quizQuestions,)
    }
    fun saveAnswers(){
        /*
        val id = mUserPresenter.currentUser?.id
        println("CURREN USER SAVE:${id}")
        if(id != null){
            mPresenter.saveAnswers(checkAnswerList, id)

        }

        MainActivity.INSTANCE.router.backTo(Screens.PatientScreen())
         */
    }

    override fun onPause() {
        super.onPause()
    }

    fun getCheckAnswer(checkId:Int){
        if(!checkAnswerList.contains(answerList[checkId])){
            for(q:QuestionModel in quiz.quizQuestions){
                var cont = false
                for(a:AnswerModel in q.answers){
                    if(a == answerList[checkId]){
                        cont = true
                    }
                }
                if(cont){
                    for(a2:AnswerModel in q.answers){
                        if(checkAnswerList.contains(a2) && a2 != answerList[checkId]){
                            checkAnswerList.remove(a2)
                        }
                    }
                }

            }
            checkAnswerList.add(answerList[checkId])
        }
    }



    companion object {

    }

    override fun initQuizList() {}

    override fun answersSaved() {}

    override fun setUserPoint(points: Int) {
    }





}