package com.example.medical_application.ui.doctor_pages.doc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.QuizModel
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens

class QuizRecyclerAdapter (
    val isPatient:Boolean,
    val list:List<QuizModel>):
    RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder>() {

    class QuizHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mQuizTxt: TextView
        lateinit var mCountTxt: TextView
        lateinit var mQuizLayout:LinearLayoutCompat

        fun bind(quiz: QuizModel, isPatient: Boolean) {
            initView()

            mQuizTxt.text = quiz.quizName
            mCountTxt.text = quiz.quizQuestions.size.toString()

            if(isPatient){
                mQuizLayout.setOnClickListener {
                    MainActivity.INSTANCE.router.navigateTo(Screens.QuizScreen(quiz))
                }
            }

        }

        private fun initView() {
            mQuizTxt = itemView.findViewById(R.id.quiz_txt_id)
            mCountTxt = itemView.findViewById(R.id.question_count_id)
            mQuizLayout = itemView.findViewById(R.id.quiz_setting_layout)


        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.quiz_setting_item, parent, false)
        return QuizHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuizHolder, position: Int) {
        holder.bind(list[position], isPatient)

    }


}
