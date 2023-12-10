package com.example.medical_application.ui.patient_pages.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel

class PatientAnswerAdapter (
    val list:List<AnswerModel>):
    RecyclerView.Adapter<PatientAnswerAdapter.QuestionHolder>() {

    class QuestionHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mQuestionTxt: TextView
        lateinit var mAnswerRecycler: RecyclerView

        fun bind(answer:AnswerModel) {
            initView()
            mQuestionTxt.text = answer.answer

        }

        private fun initView() {
            mQuestionTxt= itemView.findViewById(R.id.patient_question_txt)
            mAnswerRecycler = itemView.findViewById(R.id.patient_question_txt)


        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            QuestionHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.patient_answer_item, parent, false)
        return QuestionHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(list[position])

    }


}
