package com.example.medical_application.ui.patient_pages.patient

import android.os.Bundle
import android.telephony.RadioAccessSpecifier
import com.example.medical_application.MyApp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.medical_application.R
import com.example.medical_application.data.models.QuizData
import com.example.medical_application.data.models.UserData
import com.example.medical_application.data.repositories.QuizRepository
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.google.firebase.Timestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import java.util.UUID

class AddNoteFragment constructor(userData: UserData) :  MvpAppCompatFragment() {
    val user = userData
    lateinit var mSaveNoteBtn: AppCompatButton
    lateinit var mRadioAnswer:RadioGroup
    lateinit var mTxt1: EditText
    lateinit var mTxt2: EditText
    lateinit var mTxt3: EditText
    lateinit var mErrorTxt: TextView
    val quizRepository = QuizRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        initView(view)
        return view
    }


    fun initView(view: View) {
        view.apply {
            mSaveNoteBtn = findViewById(R.id.save_note_button)
        mRadioAnswer = findViewById(R.id.radio_answer_group)
            mTxt1 = findViewById(R.id.answer_txt_1)
            mTxt2 = findViewById(R.id.answer_txt_2)
            mTxt3 = findViewById(R.id.answer_txt_3)
            mErrorTxt = findViewById(R.id.answer_error_txt)
        }


        mSaveNoteBtn.setOnClickListener {
            if(!validationFields()){
                mErrorTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mErrorTxt.visibility = View.GONE
            saveQuiz(quizData = QuizData(answers = arrayListOf<String>(
                (view.findViewById<RadioButton>(mRadioAnswer.checkedRadioButtonId)).text.toString(),
                mTxt1.text.toString(),
                mTxt2.text.toString(),
                mTxt3.text.toString()

            ), createAt = Timestamp.now(),
                userId = user.userId,
                uuid =  UUID.randomUUID().toString()
            ))
        }
    }

    fun validationFields(): Boolean{
        return (mRadioAnswer.checkedRadioButtonId != -1 && mTxt1.text.isNotEmpty() && mTxt2.text.isNotEmpty() && mTxt3.text.isNotEmpty())
    }

    fun saveQuiz(quizData: QuizData){
        CoroutineScope(Dispatchers.IO).launch {
            val result = quizRepository.saveQuiz(quizData)
            if(result){
               withContext(Dispatchers.Main){
                   MainActivity.INSTANCE.router.replaceScreen(Screens.PatientScreen(user, false))
               }
            }
        }
    }
}