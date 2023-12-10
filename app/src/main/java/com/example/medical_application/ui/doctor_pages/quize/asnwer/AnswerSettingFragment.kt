package com.example.medical_application.ui.doctor_pages.quize.asnwer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.QuestionSettingPresenter
import com.example.medical_application.ui.view.CreateQuestionView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class AnswerSettingFragment: MvpAppCompatFragment(),
    CreateQuestionView {
    lateinit var  answerEdTxt: AppCompatEditText
    lateinit var  pointEdTxt: AppCompatEditText
    lateinit var saveBtn: AppCompatButton
    lateinit var mErrorTxt: TextView

    @Inject
    lateinit var mPresenter: QuestionSettingPresenter

    @InjectPresenter()
    lateinit var presenter: QuestionSettingPresenter


    @ProvidePresenter
    fun provideQuestionSettingPresenter(): QuestionSettingPresenter {
        return  mPresenter
    }
    fun mPresenter() = QuestionSettingPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        super.onCreate(savedInstanceState)
    }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_answer_setting, container, false)
   initView(view)
    return  view
    }

    private fun initView(view: View){
        view.apply {
            answerEdTxt = findViewById(R.id.add_answer_ed)
            pointEdTxt = findViewById(R.id.add_point_ed)
            saveBtn = findViewById(R.id.save_answer_btn)
            mErrorTxt = findViewById(R.id.answer_setting_error)

        }

        saveBtn.setOnClickListener {
            if(answerEdTxt.text.isNullOrEmpty() || pointEdTxt.text.isNullOrEmpty()){
                mErrorTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mErrorTxt.visibility = View.INVISIBLE
            mPresenter.addAnswer(AnswerModel(answerEdTxt.text.toString(),
                points = pointEdTxt.text.toString().toInt()))
            MainActivity.INSTANCE.router.backTo(Screens.QuestionScreen())
        }


    }



    override fun addAnswerToRecycler(answer: AnswerModel) {}
    override fun addQuestion(question: QuestionModel) {}

}




