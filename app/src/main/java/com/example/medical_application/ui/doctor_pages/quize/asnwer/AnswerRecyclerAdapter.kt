package com.example.medical_application.ui.doctor_pages.quize.asnwer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.AnswerModel

class AnswerRecyclerAdapter(val list:List<AnswerModel>):
    RecyclerView.Adapter<AnswerRecyclerAdapter.AnswerHolder>(){

    class AnswerHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mAnswerTxt: TextView
        lateinit var mPointTxt: TextView

            fun bind(answer: AnswerModel){
                initView()

                mAnswerTxt.text = answer.answer
                mPointTxt.text = answer.points.toString()

            }

        private fun initView(){
            mAnswerTxt = itemView.findViewById(R.id.answer_ed_id)
            mPointTxt = itemView.findViewById(R.id.point_ed_id)
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.answer_setting_item, parent, false)
        return  AnswerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.bind(list[position])

    }


}