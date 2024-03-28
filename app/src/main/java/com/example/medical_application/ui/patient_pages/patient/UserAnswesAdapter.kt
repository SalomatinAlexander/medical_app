package com.example.medical_application.ui.patient_pages.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuizData
import java.text.SimpleDateFormat


class UserAnswersAdapter (
    val list:List<QuizData>):
    RecyclerView.Adapter<UserAnswersAdapter.AnswersHolder>() {

    class AnswersHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mAnswerTxt: TextView

        fun bind(quiz: QuizData) {
            initView()
                var answers: String = ""
                if(quiz.answers == null) return
                for(a in quiz.answers){
                    answers += "\n$a"
                }
            mAnswerTxt.text = answers


        }

        private fun initView() {
            mAnswerTxt = itemView.findViewById(R.id.user_answer_txt)
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AnswersHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_answer_view, parent, false)
        return AnswersHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AnswersHolder, position: Int) {
        holder.bind(list[position])

    }


}