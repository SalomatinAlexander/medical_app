package com.example.medical_application.ui.patient_pages.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel
import com.example.medical_application.data.models.QuestionModel
import com.example.medical_application.ui.presenter.CreateQuizPresenter
import kotlin.random.Random

class PatientQuizRecyclerAdapter (
    val fr: QuizFragment,
    val list:List<QuestionModel>,
    ):
    RecyclerView.Adapter<PatientQuizRecyclerAdapter.QuestionHolder>() {
    companion object{
        val checkedList = ArrayList<Int>()
        var answerCount = -1;
    }


    class QuestionHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mQuestionTxt: TextView


        lateinit var mGroupAnswer:RadioGroup

        fun bind(question: QuestionModel, fr: QuizFragment, position: Int) {
            initView()
            mGroupAnswer.clearCheck()
            mGroupAnswer.removeAllViews()


            mQuestionTxt.text = question.question

                for(answer:AnswerModel in question.answers){
                    answerCount +=1
                    val btn = RadioButton(fr.context)
                    btn.text = answer.answer
                    println("btnid:${question.answers.indexOf(answer)}")
                    btn.id = answerCount
                    println("ID IS:${question.answers.indexOf(answer)}, btnId:${btn.id}, position:${position}")
                    mGroupAnswer.addView(btn)
                }

            mGroupAnswer.setOnCheckedChangeListener { group, checkedId ->

                fr.getCheckAnswer(checkedId)


            }
            mGroupAnswer.allViews.toList().size

        }

        private fun initView() {
            mQuestionTxt= itemView.findViewById(R.id.patient_question_txt)
            mGroupAnswer = itemView.findViewById(R.id.patient_radio_group)



        }


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.patient_quiz_item, parent, false)
        val g = itemView.findViewById<RadioGroup>(R.id.patient_radio_group)
        g.clearCheck()
        g.removeAllViews()
        g.removeAllViewsInLayout()
        return QuestionHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(list[position], fr, position)

    }


}
