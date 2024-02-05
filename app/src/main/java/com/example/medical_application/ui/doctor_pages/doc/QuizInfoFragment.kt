package com.example.medical_application.ui.doctor_pages.doc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.medical_application.R
import com.example.medical_application.data.repositories.QuizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text


class QuizInfoFragment : Fragment() {
    lateinit var userId:String;


    fun QuizInfoFragment(id: String){
        userId =id
    }
    lateinit var mFirstDocTxt:TextView
    lateinit var mDocInfo:TextView
    val quizRepository:QuizRepository = QuizRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_quiz_info, container, false)
        initView(view)
        return view

    }

    fun initView(view: View){
        view.apply {
            mFirstDocTxt = findViewById(R.id.first_doc_txt)
            mDocInfo = findViewById(R.id.doc_info)
        }
        initQuizCalendar()

        mFirstDocTxt.setOnClickListener {

        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun initQuizCalendar(){
        CoroutineScope(Dispatchers.IO).launch {
         val quizList =  quizRepository.getQuizList(userId)
            withContext(Dispatchers.Main){
                mDocInfo.text = quizList?.first().toString()
            }

        }
    }

}