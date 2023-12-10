package com.example.medical_application.ui.doctor_pages.quize

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.ui.doctor_pages.quize.asnwer.AnswerRecyclerAdapter

class QuestionRecyclerAdapter(val list:List<QuestionModel>):
    RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionHolder>() {

    class QuestionHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mQuestionTxt: TextView
        lateinit var mCountTxt: TextView

        fun bind(question: QuestionModel) {
            initView()

            mQuestionTxt.text = question.question
            mCountTxt.text =  question.answers.size.toString()

        }

        private fun initView() {
            mQuestionTxt = itemView.findViewById(R.id.question_txt_id)
            mCountTxt = itemView.findViewById(R.id.answer_count_id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.question_setting_item, parent, false)
        return QuestionHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(list[position])

    }


}